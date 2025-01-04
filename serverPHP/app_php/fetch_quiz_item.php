<?php

require_once "conn.php";


$response = array();
$quiz_item = array();

$profile_id = $_POST["profile_id"];

//Fetch quiz items
$stmt = $conn->query("SELECT id_quiz, quiz_name FROM tbl_quiz");
$rows = $stmt->num_rows;

$stmt2 = $conn->query("SELECT quiz_score FROM tbl_score WHERE tbl_profile_id_profile=$profile_id");



if ($rows > 0) {

	while(($row = $stmt->fetch_assoc()) && ($row2 = $stmt2->fetch_assoc())) {
		
		$quiz_item["id"] = $row["id_quiz"];
		$quiz_item["name"] = $row["quiz_name"];
		$quiz_item["score"] = $row2["quiz_score"];
		//$quiz_item["content"] = $row["course_content"];

		array_push($response, $quiz_item);
	}

}

echo json_encode($response);
$stmt->close();

?>