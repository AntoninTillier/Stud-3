<?php
require_once "autoload.php";
require_once "session.php";

$ID = $_SERVER["QUERY_STRING"];
$viewID = $_POST["view"];
$_POST["timestamp"] = time();
$_POST["id"] = $ID;

if (empty($ID)) {
    header("Location: index.php");
    exit();
}

try {
    $bdd = new PDO('mysql:host=localhost;dbname=pws;charset=utf8', 'pws', 'pws');
} catch (Exception $e) {
    die("Il est impossible d'accéder aux donnés pour l'instant");
}

$comment = new Comment($_POST);
$comment->update($bdd);

header("Location: view.php?$viewID");
exit();
