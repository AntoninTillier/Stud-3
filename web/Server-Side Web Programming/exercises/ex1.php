<!-- // code 1

  <html>
      <head>
          <title>TP1 PWS</title>
      </head>
      <body>
          <?php echo "<p>Bonjour PHP !</p>"; ?>
      </body>
  </html>

  // code 2

  <html>
  <?php echo "<br>";
$fruit = 'pomme';
echo "J'ai une $fruit. <br>";
echo "J'ai deux $fruits. <br>";
echo "J'ai trois ${fruit}s.<br>";
echo "\$fruit = $fruit <br>";
echo $fruit[1] . "<br>";
echo "$fruit[1] <br>";
echo "<br>"; ?>
  </html>

  // code 3

  <?php $nombres = [];
echo "<br>";
echo "<br>";
for ($i = 0; $i < 5; ++$i) {
    $nombres[] = $i * 5;
}
$nombres[3] = 'trois';
$nombres['trois'] = 3;
unset($nombres[2]); // Détruit la référence sur l'élément
$nombres[] = [
    'a' => 'A',
    'b' => 'B',];
echo '<pre>' . PHP_EOL;
affichageArray($nombres, "");
echo '</pre>' . PHP_EOL;
function affichageArray(array $element, $parent)
{
    $separateur = " => ";

    foreach ($element as $cle => $enfant) {
        if (!is_array($enfant)) {
            echo "$parent$cle$separateur$enfant" . PHP_EOL;
        } else {
            affichageArray($enfant, "$parent$cle$separateur");
        }

    }
}

echo "<br>";
echo "<br>"; ?>

  // exo 2

  <?php echo "<br>";
echo "<br>";
var_dump(array_keys($nombres));
echo "<br>";
echo "<br>"; ?>

  // exo 3
  <?php echo "<br>";
echo "<br>"; ?>
  <html>
    <body>
      <form>
        <?php
for ($i = 1; $i <= 5; $i++) {
    echo "$i\t<input type=\"text\" name=\"input$i\" value=\"Champs $i\"><br>";
}
?>
        <br>
        <input type="submit" name="submit" value="Soumettre">
      </form>
    </body>
  </html>

  // exo 4
  <?php echo "<br>";
echo "<br>"; ?>
  <html>
    <body>
      <form action="TP1 PSW.php" method="post">
        <?php
echo "A <input type=\"text\" name=\"year\"><br>";
echo "M <input type=\"text\" name=\"month\"><br>";
echo "J <input type=\"text\" name=\"day\"><br>";
echo "H <input type=\"text\" name=\"hour\"><br>";
echo "M <input type=\"text\" name=\"minute\"><br>";
echo "S <input type=\"text\" name=\"second\"><br>";
?>
        <br>
        <input type="submit" name="submit" value="Soumettre">
        <br>
        <br>
        <?php
echo $_POST['year'] . "\t";
echo $_POST['month'] . "\t";
echo $_POST['day'] . "\t";
echo $_POST['hour'] . "\t";
echo $_POST['minute'] . "\t";
echo $_POST['second'] . "\t";
?>
      </form>
    </body>
  </html> -->

// exo 5
<?php
$nombres = [];
for ($i = 0; $i < 5; ++$i) {
    $nombres[] = $i * 5;
}
$nombres[3] = 'trois';
$nombres['trois'] = 3;
unset($nombres[2]);
$nombres[] = [
    'a' => 'A',
    'b' => 'B',
];
function Recursive($nombres)
{
    if (count($nombres) == 1)
        return array($nombres[0]);
    else {
        $res = array();
        $i = 0;
        foreach ($nombres as $val) {
            if ($i != 0)
                $res[] = $val;
            $i++;
        }
        $outp = Recursive($res);
        $outp[] = $nombres[0];
        return $outp;
    }
}

$reversed = Recursive($nombres);

var_dump($reversed);

?>
