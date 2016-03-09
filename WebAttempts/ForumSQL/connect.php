<?php
//connect.php
$server	= 'localhost';
$username	= 'cemersoz';
$password	= '564164';
$database	= 'sql1.sql';

if(!mysql_connect($server, $username,  $password))
{
 	exit('Error: could not establish database connection');
}
if(!mysql_select_db($database)
{
 	exit('Error: could not select the database');
}
?>