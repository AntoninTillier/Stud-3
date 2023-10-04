<?php
echo("<br>");
$CAPTION = "Modifier le compte";
require_once "autoload.php";
require_once "session.php";
require_once "header.phtml";


try {
    $bdd = new PDO('mysql:host=localhost;dbname=pws;charset=utf8', 'pws', 'pws');
} catch (Exception $e) {
    die("Il est impossible d'accéder aux donnés pour l'instant");
}

$req = $bdd->prepare('SELECT * FROM user WHERE username=?');
$req->execute(array($_SESSION["user"]));
$data = $req->fetch();
$user = new User($data);
$req->closeCursor();

$e = $_SERVER["QUERY_STRING"];
$error = explode("-", $e);

$emdp = "";
$eamdp = "";
$ename = "";
$eemail = "";

foreach ($error as $key => $value) {
    if ($value == "mdp") {
        $emdp = $value;
    }
    if ($value == "name") {
        $ename = $value;
    }
    if ($value == "email") {
        $eemail = $value;
    }
    if ($value == "amdp") {
        $eamdp = $value;
    }
}

require_once "user-update.phtml";
require_once "footer.phtml";
