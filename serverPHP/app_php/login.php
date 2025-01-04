<?php

require_once "conn.php";
require_once "helper_class.php";

//Array for response data
$response = array();
$result = new Result();

$jsonData = file_get_contents('php://input');
$data = json_decode($jsonData, true);

if ($data !== NULL) {
	$name = $data["name"];
	$password = $data["password"];

	//Get password to verify
	$stmt = $conn->prepare("SELECT password FROM tbl_user WHERE name=?");
	$stmt->bind_param("s", $name);
	$stmt->execute();
	$stmt->store_result();
	$stmt->bind_result($db_pass);
	$stmt->fetch();
	$rows = $stmt->num_rows;
	$stmt->close();

	// if user exists
	if ($rows>0) {
		// and if password verified 
		// TODO: use password_verify once DB altered
		if ($password == $db_pass) {

			//Fetch user data
			$stmt = $conn->prepare("SELECT id_user, name FROM tbl_user WHERE name=?");
			$stmt->bind_param("s", $name);
			$stmt->execute();
			$stmt->store_result();
			$stmt->bind_result($id_user, $name);
			$stmt->fetch();
			 
			//Fetch user's profile
			$stmt = $conn->prepare("SELECT id_profile, tests_taken, tests_passed FROM tbl_profile WHERE tbl_user_id_user=?");
			$stmt->bind_param("i", $id_user);
			$stmt->execute();
			$stmt->store_result();
			$stmt->bind_result($id_profile, $tests_taken, $tests_passed);
			$stmt->fetch();

			$stmt->close();

			$response["user"]["name"] = $name;

			$response["profile"]["profile_id"] = $id_profile;
			$response["profile"]["tests_taken"] = $tests_taken; 
			$response["profile"]["tests_passed"] = $tests_passed;


			$result->setErrorStatus(false);
			$result->setMessage("Login success");
		} else {
			$result->setErrorStatus(true);
			$result->setMessage("Invalid credentials");
		}
	} else {
		$result->setErrorStatus(true);
		$result->setMessage("Invalid credentials");
	}
} else { 
	//redundant
	$result->setErrorStatus(true);
	$result->setMessage("Missing parameters");
}

$response["result"]["error"] = $result->isError();
$response["result"]["message"] = $result->getMessage();

echo json_encode($response);
?>
