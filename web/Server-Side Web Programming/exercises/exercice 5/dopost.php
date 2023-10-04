<?php

if (!strlen($_POST["nom"]) || !strlen($_POST["motdepasse"])) {
    echo "Invalid username and password.";
    exit();
}

$filename = md5(strtoupper($_POST["nom"])) . ".txt";

$data["nom"] = $_POST["nom"];
$data["motdepasse"] = md5(strtoupper($_POST["motdepasse"]));
$data["photo"] = "photo.jpg"; //$_POST["photo"];
$data["marque"] = $_POST["marque"];
$data["carburant"] = $_POST["carburant"];
$data["moteur"] = $_POST["moteur"];
$data["commentaire"] = $_POST["commentaire"];
$data["invisible"] = $_POST["invisible"];

echo "<pre>";
var_dump($data);
echo "<pre>";
$f = fopen("data/$filename", "w");

foreach ($data as $key => $value) {
    $line = "$key:";
    if (is_array($value)) {
        foreach ($value as $sub_key => $sub_value) {
            $line .= $sub_key . "-";
        }
    } else {
        $line .= $value;
    }
    fwrite($f, "$line\n");

}
fclose($f);

echo '<p>Fiche enregistrée.</p>';
echo '<p><a href="form.html">Retourner</a></p>';
