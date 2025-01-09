<?php

require_once "conn.php";
require_once "helper_class.php";

//Array for response data
$response = array();
$result = new Result();


if (!empty($_POST["quiz_id"] && !empty($_POST['profile_id']))) {

	$quiz_id = $_POST["quiz_id"];
	$profile_id = $_POST["profile_id"];

	// Get quiz data
	$stmt = $conn->prepare("SELECT quiz_name, tbl_course_id_course FROM tbl_quiz WHERE id_quiz=?");
	$stmt->bind_param("i", $quiz_id);
	$stmt->execute();
	$stmt->store_result();
	$stmt->bind_result($quiz_name, $quiz_course_id);
	$stmt->fetch();
	$stmt->close();

	$response["quiz_data"]["id"] = $quiz_id;
	$response["quiz_data"]["quizCourseId"] = $quiz_course_id;
	$response["quiz_data"]["name"] = $quiz_name;

	// Get course data
	$stmt = $conn->prepare("SELECT course_name FROM tbl_course WHERE id_course=?");
	$stmt->bind_param("i",$quiz_course_id);
	$stmt->execute();
	$stmt->store_result();
	$stmt->bind_result($course_name);
	$stmt->fetch();
	$stmt->close();

	$response["course_data"]["name"] = $course_name;

	// Get count of questions belonging to specific quiz
	$stmt = $conn->prepare("SELECT COUNT(*) AS questions_count FROM tbl_question WHERE tbl_quiz_id_quiz=?");
	$stmt->bind_param("i",$quiz_id);
	$stmt->execute();
	$stmt->store_result();
	$stmt->bind_result($questions_amount);
	$stmt->fetch();
	$stmt->close();

	$response["score"]["questionAmount"] = $questions_amount;

	//Get the score using profile id and quiz id.
	$stmt = $conn->prepare("SELECT quiz_score FROM tbl_score WHERE tbl_quiz_id_quiz=? AND tbl_profile_id_profile=?");
	$stmt->bind_param("ii",$quiz_id, $profile_id);
	$stmt->execute();
	$stmt->store_result();
	$stmt->bind_result($quiz_score);
	$stmt->fetch();
	$stmt->close();

	$response["score"]["score"] = $quiz_score;
	$response["score"]["profile"] = $profile_id;

	$result->setErrorStatus(false);
	$result->setMessage("Data retrieved");

} else {
	$result->setErrorStatus(true);
	$result->setMessage("No data retrieved");
}

$response["result"]["error"] = $result->isError();
$response["result"]["message"] = $result->getMessage();

echo json_encode($response);

?>