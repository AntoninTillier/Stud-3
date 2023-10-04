<?php

if (!isset($_POST["name"])) {
    header("Location: index.php");
    exit();
}

$name = urlencode($_POST["name"]);
$note = $_POST["note"];
$text = $_POST["text"];
$time = time();
$code = md5($name . $note . $time);

$indexFile = "data/index.txt";
$contentFile = "data/$code.txt";

// Exercice 1

$f1 = fopen($indexFile, 'a+');
fputs($f1, $name . ',' . $note . ',' . $time . ',' . $code . '.');

$f2 = fopen($contentFile, 'w');
fwrite($f2, $text);

header("Location: index.php");
exit();
