<?php
require_once "autoload.php";

if (!isset($_POST["submit"])) {
    header("location:" . $_SERVER['HTTP_REFERER']);
    exit();
}

session_start();
session_destroy();

if (verif()) {
    session_start();
    $_SESSION["last"] = time();
    $_SESSION['user'] = $_POST["username"];
}

header("Location: index.php");
exit();

function verif()
{
    $mdp = "";
    $name = "";
    $email = "";
    $user = new User($_POST);
    if ($_POST["password"] != $_POST["passwordx"]) {
        $mdp = "mdp";
    }
    if (filter_var($_POST["email"], FILTER_VALIDATE_EMAIL) == false) {
        $email = "email";
    }
    try {
        $bdd = new PDO('mysql:host=localhost;dbname=pws;charset=utf8', 'pws', 'pws');
    } catch (Exception $e) {
        die("Il est impossible d'accéder aux donnés pour l'instant");
    }

    $req = $bdd->prepare('SELECT username FROM user WHERE username=?');
    $req->execute(array($user->getUsername()));

    if ($data = $req->fetch()) {
        $req->closeCursor();
        $name = "name";
    } else {
        if (empty($mdp) == true && empty($name) == true && empty($email) == true) {
            $req->closeCursor();
            $user->insert($bdd);
            return true;
        }
    }
    header("Location: sign-up.php?$mdp-$name-$email");
    exit();
}
