<?php

function inverse($s)
{
    $rs = $s;
    $rs = preg_replace_callback(
        "/([a-zA-Z])/",
        function ($matches) {
            return preg_match("/([a-z])/", $matches[0]) == 1 ? strtoupper($matches[0]) : strtolower($matches[0]);
        },
        $rs);
    return $rs;
}

$str = "AdhnksAncdkjshAgeiygfddtryfgsuhskfAjnsdkYFZTYFSVHJK";

echo "<p>";
echo inverse($str);
