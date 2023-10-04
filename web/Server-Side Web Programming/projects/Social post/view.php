<?php
require_once "autoload.php";
require_once "session.php";
echo("<br>");
$CAPTION = "Annonce";

$postID = $_SERVER["QUERY_STRING"];

function isOwner($comment)
{
    return ($comment->getUsername() == $_SESSION['user']);
}

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


$req = $bdd->query('SELECT * FROM comment');
$comments = [];
$n = 0;
while ($data = $req->fetch()) {
    $comments[$n] = new Comment($data);
    $n++;
}
$comments = $post->getComments($bdd);

require_once "header.phtml";
require_once "view.phtml";
require_once "footer.phtml";
