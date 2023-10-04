<?php
require_once "autoload.php";
require_once "session.php";

function isOwner($post)
{
    return ($post->getUsername() == $_SESSION['user']);
}

try {
    $bdd = new PDO('mysql:host=localhost;dbname=pws;charset=utf8', 'pws', 'pws');
} catch (Exception $e) {
    die("Il est impossible d'accéder aux donnés pour l'instant");
}

$posts = [];
$req = $bdd->query('SELECT * FROM post');
$n = 0;
while ($data = $req->fetch()) {
    $posts[$n] = new Post($data);
    $n++;
}
$req->closeCursor();

$CAPTION = "";
require_once "header.phtml";
require_once "list.phtml";
require_once "footer.phtml";
