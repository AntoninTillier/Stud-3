<?php
echo("<br>");
$CAPTION = "Modifier le commentaire : ";
require_once "autoload.php";
require_once "session.php";
require_once "header.phtml";

$commentID = $_SERVER["QUERY_STRING"];

try {
    $bdd = new PDO('mysql:host=localhost;dbname=pws;charset=utf8', 'pws', 'pws');
} catch (Exception $e) {
    die("Il est impossible d'accéder aux donnés pour l'instant");
}

$req = $bdd->prepare('SELECT * FROM comment WHERE id=?');
$req->execute(array($commentID));
$data = $req->fetch();
$comment = new Comment($data);
$req->closeCursor();

require_once "comment-update.phtml";
require_once "footer.phtml";
