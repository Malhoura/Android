<?php
$con = mysqli_connect("mysql2.000webhost.com", "a3194582_mazen", "techzing1992", "a3194582_User");

$username = $_POST["username"];
$password = $_POST["password"];

$statement = mysqli_prepare($con, "SELECT * FROM User WHERE username= ? AND password= ?");
mysqli_stmt_bind_param($statement, "ss", $username, $password);
mysqli_stmt_execute($statement);

mysqli_stmt_store_result($statement);
mysqli_stmt_bind_result($statement,$userID, $username, $emailaddress, $password);

//create an array which hold the data from the returned user
$user = array();
while(mysqli_stmt_fetch($statement)){
	$user[username] = $username;
	$user[emailaddress] = $emailaddress;
	$user[password] = $password;
}

//send the array to the phone so the phone can recieve the data
echo json_encode($user);

mysqli_stmt_close($statement);

mysqli_close($con);
?>