<?php

$servername = "localhost:3306";
$dbusername = "root";
$dbpassword = "";
$db = "trainingappschema";
;

$conn = mysqli_connect($servername, $dbusername, $dbpassword, $db);

//Check connection
if (!$conn) {
   die( "Connection error:" . mysqli_connect_error());
}

?>