<?php

function highlight($r, $rr)
{
    $rs = $r;
    $rs = preg_replace("/($rr)/", "<b>$rr</b>", $rs);
    return $rs;
}

$str = "AdhnksAncdkjshAgeiygfddtryfgsuhskfAjnsdkYFZTYFSVHJK";

echo "<p>";
echo highlight($str, "a");
echo "<p>";
echo highlight($str, "A");
echo "<p>";
echo highlight($str, "try");
echo "<p>";
echo highlight($str, "TRY");
