<?php

require_once "conn.php";
require_once "helper_class.php";

//Array for response data
$response = array();
$result = new Result();


if (!empty($_POST["name"]) && !empty($_POST["password"])) {
	$name = $_POST["name"];
	$password = $_POST["password"];

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

			//TODO: remove the id's being fetched, remember to do so in the corresponding class models.
			$response["user"]["id"] = $id_user;
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
	$result->setErrorStatus(true);
	$result->setMessage("Missing parameters");
}

$response["result"]["error"] = $result->isError();
$response["result"]["message"] = $result->getMessage();

echo json_encode($response);
?>
