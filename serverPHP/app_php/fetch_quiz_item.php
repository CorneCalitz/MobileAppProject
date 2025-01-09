<?php

require_once "conn.php";

$response = array();
$quiz_item = array();

//Fetch quiz items
$stmt = $conn->query("SELECT id_quiz, quiz_name, tbl_course_id_course FROM tbl_quiz");
$rows = $stmt->num_rows;

if ($rows > 0) {

	while($row = $stmt->fetch_assoc()) {
		
		$quiz_item["id"] = $row["id_quiz"];
		$quiz_item["name"] = $row["quiz_name"];
		$quiz_item["quiz_course_id"] = $row["tbl_course_id_course"];
		

		array_push($response, $quiz_item);
	}

}

$stmt->close();
echo json_encode($response);


?>