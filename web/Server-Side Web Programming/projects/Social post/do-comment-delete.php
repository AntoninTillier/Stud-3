<?php
require_once "autoload.php";
require_once "session.php";

$ID = $_SERVER["QUERY_STRING"];
$msg = true;

if (empty($ID)) {
    header("Location: index.php");
    exit();
}

try {
    $bdd = new PDO('mysql:host=localhost;dbname=pws;charset=utf8', 'pws', 'pws');
} catch (Exception $e) {
    echo("Supression du commentaire n'a pas pu être éffectué.");
    $msg = false;
}

$comment = new Comment($ID);
$comment->delete($bdd);
if ($msg) {
    echo("Supression du commentaire éffectué.");
}

exit();
