<?php
echo("<br>");
$CAPTION = "Créer un compte";
require_once "header.phtml";

$e = $_SERVER["QUERY_STRING"];
$error = explode("-", $e);

$emdp = "";
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
}

require_once "sign-up.phtml";
require_once "footer.phtml";
