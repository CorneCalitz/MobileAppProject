<?php

require_once "conn.php";

$response = array();
$question_data = array();

if (!empty($_POST["id"])) {

	$quiz_id = $_POST["id"];

	//Fetch quiz items
	$stmt = $conn->prepare("SELECT id_question, question_asked, question_context, question_answer FROM tbl_question WHERE tbl_quiz_id_quiz=?");
	$stmt->bind_param("i", $quiz_id);
	$stmt->execute();
	$stmt->bind_result($id, $question, $context, $answer);

	while($stmt->fetch()) {
		
		$question_data["id"] = $id;
		$question_data["question"] = $question;
		$question_data["context"] = $context;
		$question_data["answer"] =  $answer;
		//May need quizID for other stuff.

		array_push($response, $question_data);

	}
	$stmt->close();

}

echo json_encode($response);


?>