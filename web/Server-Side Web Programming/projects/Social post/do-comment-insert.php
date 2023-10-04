<?php
require_once "autoload.php";
require_once "session.php";

if (!isset($_POST["submit"])) {
    header("Location: index.php");
    exit();
}

try {
    $bdd = new PDO('mysql:host=localhost;dbname=pws;charset=utf8', 'pws', 'pws');
} catch (Exception $e) {
    die("Il est impossible d'accéder aux donnés pour l'instant");
}

$comment = [];
$postID = $_SERVER["QUERY_STRING"];
$content = $_POST["content"];

$comment["post_id"] = $postID;
$comment["content"] = $content;

$req = $bdd->prepare("SELECT id from user where username=?");
$req->execute(array($_SESSION['user']));

$data = $req->fetch();

$user_id = $data["id"];

$req->closeCursor();


try {
    $bdd = new PDO('mysql:host=localhost;dbname=pws;charset=utf8', 'pws', 'pws');
} catch (Exception $e) {
    die("Il est impossible d'accéder aux donnés pour l'instant");
}

$req = $bdd->prepare('SELECT username FROM user WHERE id=?');
$req->execute(array($user_id));

$data = $req->fetch();
$comment['username'] = $data['username'];

$req->closeCursor();

$comment["user_id"] = $user_id;
$comment['timestamp'] = time();

$com = new Comment($comment);

$com->insert($bdd);

header("location:" . $_SERVER['HTTP_REFERER']);
exit();
