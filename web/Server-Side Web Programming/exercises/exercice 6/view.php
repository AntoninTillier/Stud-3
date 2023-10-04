<?php

$code = $_SERVER["QUERY_STRING"];

$filename = "data/$code.txt";

if (!is_file($filename)) {
    header("Location: index.php");
    exit();
}

$text = file_get_contents($filename);

$f = fopen("data/index.txt", 'r');
$s = fread($f, filesize('data/index.txt'));

while (strlen($s) != 0) {
    $name = substr($s, 0, stripos($s, ','));
    $s = substr($s, stripos($s, ',') + 1);
    $note = substr($s, 0, stripos($s, ','));
    $s = substr($s, stripos($s, ',') + 1);
    $date = substr($s, 0, stripos($s, ','));
    $s = substr($s, stripos($s, ',') + 1);
    $cod = substr($s, 0, stripos($s, '.'));
    $s = substr($s, stripos($s, '.') + 1);

    if ($cod == $code) {
        $s = "";
    }
}

require_once "content.phtml";
