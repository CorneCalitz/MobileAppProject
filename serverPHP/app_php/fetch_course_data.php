<?php

require_once "conn.php";
require_once "helper_class.php";

//Array for response data
$response = array();
$result = new Result();

$jsonData = file_get_contents('php://input');
$data = json_decode($jsonData, true);

if ($data !== NULL) {
	$course_id = $data["id"];
	$course_name = $data["name"];

	// Get content of course matching course_id from DB
	$stmt = $conn->prepare("SELECT course_content FROM tbl_course WHERE id_course=?");
	$stmt->bind_param("i",$course_id);
	$stmt->execute();
	$stmt->store_result();
	$stmt->bind_result($course_content);
	$stmt->fetch();
	$stmt->close();


	if ($course_content != NULL) {
		$response["course_data"]["id"] = $course_id;
		$response["course_data"]["name"] = $course_name;
		$response["course_data"]["content"] = $course_content;

		// Get the quiz name and id that belongs to the course.
		$stmt = $conn->prepare("SELECT id_quiz, quiz_name FROM tbl_quiz WHERE tbl_course_id_course=?");
		$stmt->bind_param("i",$course_id);
		$stmt->execute();
		$stmt->store_result();
		$stmt->bind_result($quiz_id, $quiz_name);
		$stmt->fetch();
		$rows = $stmt->num_rows;
		$stmt->close();

		//if the course has a quiz attached to it.
		if ($rows>0) {
			$response["quiz_data"]["id"] = $quiz_id;
			$response["quiz_data"]["name"] = $quiz_name;

			$result->setErrorStatus(false);
			$result->setMessage("Content with quiz");
			
			} else {
				$result->setErrorStatus(false);
				$result->setMessage("No quiz.");
			}

	
		} else {
			$result->setErrorStatus(true);
			$result->setMessage("No content matching id");

		}

	} else {
		$result->setErrorStatus(true);
		$result->setMessage("No data retrieved");
}

$response["result"]["error"] = $result->isError();
$response["result"]["message"] = $result->getMessage();

echo json_encode($response);
?>