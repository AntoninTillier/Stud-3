<?php
echo("<br>");
$CAPTION = "Supression de post";
require_once "autoload.php";
require_once "session.php";
require_once "header.phtml";

$postID = $_SERVER["QUERY_STRING"];

try {
    $bdd = new PDO('mysql:host=localhost;dbname=pws;charset=utf8', 'pws', 'pws');
} catch (Exception $e) {
    die("Il est impossible d'accéder aux donnés pour l'instant");
}

$req = $bdd->prepare('SELECT firstname, lastname FROM user WHERE username=?');
$req->execute(array($_SESSION["user"]));
$data = $req->fetch();
$firstname = $data["firstname"];
$lastname = $data["lastname"];
$req->closeCursor();

$req = $bdd->prepare('SELECT title FROM post WHERE id=?');
$req->execute(array($postID));
$data = $req->fetch();
$titlePost = $data["title"];
$req->closeCursor();

require_once "post-delete.phtml";
require_once "footer.phtml";
