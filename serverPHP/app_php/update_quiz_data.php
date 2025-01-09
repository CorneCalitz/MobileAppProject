<?php

require_once "conn.php";

$response = array();

//Set quiz data to variables
$jsonData = file_get_contents('php://input');
$data = json_decode($jsonData, true);


try {

	$score = $data["score"]["score"];
	$quiz_id = $data["quiz_data"]["id"];
	$profile_id = $data["score"]["profile"];

	$stmt = $conn->prepare("UPDATE tbl_score SET quiz_score=? WHERE tbl_quiz_id_quiz=? AND tbl_profile_id_profile=?");
	$stmt->bind_param("iii", $score, $quiz_id, $profile_id);
	$stmt->execute();
	$stmt->close();


 	echo "Record UPDATED successfully";

	} catch(PDOException $e) {
		echo $e->getMessage();
	}

?>