<?php
$con = mysqli_connect("mysql2.000webhost.com", "a3194582_mazen", "techzing1992", "a3194582_User");

$username = $_POST["username"];
$emailaddress = $_POST["emailaddress"];
$password = $_POST["password"];

//store the values in the database 
//we put question marks instead of the actual variables to prevent sql injection
$statement = mysqli_prepare($con, "INSERT INTO User (username, emailaddress, password) VALUES (?, ?, ?)");
mysqli_stmt_bind_param($statement, "sss", $username, $emailaddress, $password);
mysqli_stmt_execute($statement);

mysqli_stmt_close($statement);


mysqli_close($con);
?>