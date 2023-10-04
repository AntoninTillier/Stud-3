<?php
require_once "autoload.php";

if (!isset($_POST["submit"])) {
    header("Location: index.php");
    exit();
}

session_start();
session_destroy();

if (doSignIn()) {
    session_start();
    $_SESSION["last"] = time();
    $_SESSION['user'] = $_POST["username"];
}


header("Location: index.php");
exit();

function doSignIn()
{
    $username = $_POST["username"];
    $password = md5($_POST["password"]);
    try {
        $bdd = new PDO('mysql:host=localhost;dbname=pws;charset=utf8', 'pws', 'pws');
    } catch (Exception $e) {
        die("Il est impossible d'accéder aux donnés pour l'instant");
    }

    $req = $bdd->prepare('SELECT username, password FROM user WHERE username=? AND password=?');
    $req->execute(array($username, $password));

    if ($data = $req->fetch()) {
        $req->closeCursor();
        return true;
    }
    $req->closeCursor();
    header("Location: sign-in.php?e");
    exit();
}
