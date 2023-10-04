<?php
require_once "autoload.php";

if (!isset($_POST["submit"])) {
    header("location:" . $_SERVER['HTTP_REFERER']);
    exit();
}

if (verif()) {
    session_start();
    session_destroy();
    session_start();
    $_SESSION["last"] = time();
    $_SESSION['user'] = $_POST["username"];
}

header("Location: index.php");
exit();

function verif()
{
    $mdp = "";
    $amdp = "";
    $name = "";
    $email = "";

    try {
        $bdd = new PDO('mysql:host=localhost;dbname=pws;charset=utf8', 'pws', 'pws');
    } catch (Exception $e) {
        die("Il est impossible d'accéder aux donnés pour l'instant");
    }
    if (empty($_POST["passwordx"])) {
        $_POST["passwordx"] = $_POST["password"];
        $req = $bdd->prepare('SELECT username, password FROM user WHERE username=? AND password=?');
        $req->execute(array($_POST["username"], md5($_POST["password"])));
        if (!$data = $req->fetch()) {
            $req->closeCursor();
            $amdp = "amdp";
        }
    }
    if ($_POST["password"] != $_POST["passwordx"]) {
        $mdp = "mdp";
    }
    if (filter_var($_POST["email"], FILTER_VALIDATE_EMAIL) == false) {
        $email = "email";
    }
    $user = new User($_POST);

    $req = $bdd->prepare('SELECT username FROM user WHERE username=?');
    $req->execute(array($user->getUsername()));

    if ($data = $req->fetch() && !($_POST["lastUsername"] == $user->getUsername())) {
        $req->closeCursor();
        $name = "name";
    } else {
        if (empty($mdp) == true && empty($name) == true && empty($email) == true && empty($amdp) == true) {
            $req->closeCursor();
            $user->update($bdd);
            return true;
        }
    }
    header("Location: user-update.php?$mdp-$name-$email-$amdp");
    exit();
}
