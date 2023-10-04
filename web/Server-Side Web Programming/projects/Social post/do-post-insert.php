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

$titre = $_POST['title'];
$content = $_POST['content'];
$photo = $_FILES['photo'];
if ($photo['name'] != "") {
    $path = "img/" . $photo['name'];
    $ret = copy($photo['tmp_name'], $path);
    $_POST['photo'] = base64_encode($path);
}
$req = $bdd->prepare('SELECT id FROM user WHERE username=?');
$req->execute(array($_POST['username']));
$data = $req->fetch();
$_POST['user_id'] = $data["id"];
$_POST["timestamp"] = time();
$req->closeCursor();
$post = new Post($_POST);
$post->insert($bdd);

header("Location: list.php");
exit();
