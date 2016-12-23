<?php

$hostname = "localhost";
$password = "";
$username = "root";
$db = "APKAUPDATE";

$firno = $_POST['firno'];
$district = $_POST['district'];
$station = $_POST['station'];
$officer = $_POST['officer'];
$mobile = $_POST['mobile'];
$query = $_POST['query'];
$datereg = $_POST['date'];
$timereg = $_POST['time'];

$conn = mysqli_connect($hostname,$username,$password,$db);

if(!$conn) {
  echo "Connection Unsuccessful".'<br>'.mqsqli_connect_error($conn);
  die();
} else {
  $quer = "INSERT INTO FIRTABLE values('$firno','$district','$station','$officer','$mobile','$query','$datereg','$timereg')";

//  $quer = "INSERT INTO FIRTABLE values('67437','OUTER','SEEMAPURI','KRISHNA RAI',9818351018,'You have stolen my phone','22/12/2016','16:42');";

  if(mysqli_query($conn,$quer)) {
    echo "Data Uploaded!!";
  } else {
    echo "Data not uploaded ".mysqli_error($conn);
  }
}

mysqli_close($conn);

 ?>
