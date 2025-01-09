<?php

require_once "conn.php";

//Set quiz data to variables
$jsonData = file_get_contents('php://input');
$data = json_decode($jsonData, true);


try {

	$id = $data["profile"]["profile_id"];
	$taken = $data["profile"]["tests_taken"];
	$passed = $data["profile"]["tests_passed"];

	$stmt = $conn->prepare("UPDATE tbl_profile SET tests_taken=? WHERE id_profile=?");
	$stmt->bind_param("ii", $taken, $id);
	$stmt->execute();
	

	$stmt = $conn->prepare("UPDATE tbl_profile SET tests_passed=? WHERE id_profile=?");
	$stmt->bind_param("ii", $passed, $id);
	$stmt->execute();

	$stmt->close();


 	echo "Record UPDATED successfully";

	} catch(PDOException $e) {
		echo $e->getMessage();
	}

?>