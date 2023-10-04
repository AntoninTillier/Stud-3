<?php
require_once "autoload.php";
require_once "session.php";

$ID = $_SERVER["QUERY_STRING"];

if (empty($ID)) {
    header("Location: index.php");
    exit();
}

try {
    $bdd = new PDO('mysql:host=localhost;dbname=pws;charset=utf8', 'pws', 'pws');
} catch (Exception $e) {
    die("Il est impossible d'accéder aux donnés pour l'instant");
}
$req = $bdd->prepare('SELECT id FROM user WHERE username=?');
$req->execute(array($_POST['username']));
$data = $req->fetch();
$_POST['user_id'] = $data["id"];
$req->closeCursor();

$_POST["timestamp"] = time();
$_POST["id"] = $ID;

$titre = $_POST['title'];
$content = $_POST['content'];
$photo = $_FILES['photo'];
if (empty($photo["name"])) {
    $path = $_POST["photo"];
} else {
    $path = "img/" . $photo['name'];
}

copy($photo['tmp_name'], $path);
if (!empty($path)) {
    $_POST['photo'] = base64_encode($path);
}

$post = new Post($_POST);
var_dump($post);
$post->update($bdd);

header("Location: view.php?$ID");
exit();
