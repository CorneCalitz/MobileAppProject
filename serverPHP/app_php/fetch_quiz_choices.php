<?php

require_once "conn.php";

$response = array();
$choice_data = array();


if (!empty($_POST["id"])) {

	$question_id = $_POST["id"];

	//Fetch quiz items
	$stmt = $conn->prepare("SELECT id_choice, choice FROM tbl_choice WHERE tbl_question_id_question=?");
	$stmt->bind_param("i", $question_id);
	$stmt->execute();
	$stmt->bind_result($id, $choice);

	while($stmt->fetch()) {
		
		$choice_data["id"] = $id;
		$choice_data["choice"] = $choice;

		array_push($response, $choice_data);

	}
	$stmt->close();
	
}

echo json_encode($response);


?>