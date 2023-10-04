<?php

require_once "header.phtml";

$comments = [];

if (file_exists("data/index.txt")) {
    //$lines = file("data/index.txt");
    $lines = fopen("data/index.txt", 'r');
    // Exercice 2
    $s = fread($lines, filesize('data/index.txt'));
    while (strlen($s) != 0) {
        $name = substr($s, 0, stripos($s, ','));
        $s = substr($s, stripos($s, ',') + 1);
        $note = substr($s, 0, stripos($s, ','));
        $s = substr($s, stripos($s, ',') + 1);
        $time = substr($s, 0, stripos($s, ','));
        $s = substr($s, stripos($s, ',') + 1);
        $code = substr($s, 0, stripos($s, '.'));
        $s = substr($s, stripos($s, '.') + 1);

        $comments[$name] = [
            "name" => $name,
            "note" => $note,
            "code" => $code,
            "date" => $time
        ];
    }
}

require_once "list.phtml";

require_once "form.phtml";

require_once "footer.phtml";
