// Exercice 2
<?php
echo "<br>";
echo "<br>";
$content = file_get_contents("https://en.wikipedia.org/wiki/PHP");
$lines = 0;
$lettres = 0;
$numbers = 0;
for ($i = 0; $i < strlen($content); ++$i) {
    $c = $content[$i];
    if ($c == "\n")
        ++$lines;
    else if (($c >= "A" && $c <= "Z") || ($c >= "a" && $c <= "z"))
        ++$lettres;
    else if ($c >= "0" && $c <= "9")
        ++$numbers;
}
echo "$lines => lines, $lettres => lettres, $numbers => numbers\n";

$php = 0;
$pos = strpos($content, "PHP");
while ($pos !== FALSE) {
    ++$php;
    $pos = strpos($content, "PHP", $pos + 1);
}
echo "$php => PHP\n";
echo "<br>";
echo "<br>";
?>
// Exercice 3

<?php
echo "<br>";
echo "<br>";
$lines = file("all.txt");
$M = [];
foreach ($lines as $line) {
    $row = explode(" ", trim($line));
    $M[intval($row[0])] = $row;
}
ksort($M);
?>

<?php
echo '<table border = 1>';
foreach ($M as $row) {
    echo '<tr>';
    foreach ($row as $col) {
        echo '<td>';
        echo $col;
        echo '</td>';
    }
    echo '</tr>';
}
echo '</table>';
?>
