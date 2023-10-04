<?php
session_start();
echo "<br>";
$CAPTION = "Connexion";
require_once "header.phtml";

$e = $_SERVER["QUERY_STRING"];

require_once "sign-in.phtml";
require_once "footer.phtml";
