<?php


require_once "conn.php";

$response = array();
$course_item = array();

//Fetch course data
$stmt = $conn->query("SELECT id_course, course_name, course_desc, course_content FROM tbl_course");
$rows = $stmt->num_rows;


if ($rows > 0) {

	while($row = $stmt->fetch_assoc()) {

		$course_item["id"] = $row["id_course"];
		$course_item["name"] = $row["course_name"];
		$course_item["description"] = $row["course_desc"];
	

		array_push($response, $course_item);
	}

}

$stmt->close();
echo json_encode($response);



?>