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
    echo("n'a pas pu être éffectué.");
    $msg = false;
}

$req = $bdd->prepare('DELETE FROM comment WHERE post_id=?');
$req->execute(array($ID));
$req->closeCursor();

$post = new Post($ID);
$post->delete($bdd);
if ($msg) {
    echo("est éffectué.");
}

exit();
