<?php
echo("<br>");
$CAPTION = "Modifier l'article : ";
require_once "autoload.php";
require_once "session.php";
require_once "header.phtml";

$postID = $_SERVER["QUERY_STRING"];

try {
    $bdd = new PDO('mysql:host=localhost;dbname=pws;charset=utf8', 'pws', 'pws');
} catch (Exception $e) {
    die("Il est impossible d'accéder aux donnés pour l'instant");
}

$req = $bdd->prepare('SELECT * FROM post WHERE id=?');
$req->execute(array($postID));
$data = $req->fetch();
$post = new Post($data);
$req->closeCursor();
require_once "post-update.phtml";
require_once "footer.phtml";
