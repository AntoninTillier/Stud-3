<?php

if (!isset($_POST["submit"])) {
    require_once 'connexion.html';
    exit();
}

if (!strlen($_POST["nom"]) || !strlen($_POST["motdepasse"])) {
    header('Location: connexion.html');
}

$username = $_POST["nom"];
$mdp = md5(strtoupper($_POST["motdepasse"]));
$filename = $filename = md5(strtoupper($_POST["nom"])) . ".txt";

foreach (scandir('data') as $files) {
    if ($files != '.' && $files != '..') {
        if ($filename == $files)
            $res = file('data/' . $filename);
    }
}

$data = array();
foreach ($res as $lines) {
    $information = explode(':', $lines);
    $information[0] = str_replace("\n", "", $information[0]);
    $information[0] = str_replace("\r", "", $information[0]);
    $information[0] = str_replace("\t", "", $information[0]);
    $information[1] = str_replace("\n", "", $information[1]);
    $information[1] = str_replace("\r", "", $information[1]);
    $information[1] = str_replace("\t", "", $information[1]);
    if (stristr($information[1], "-")) {
        $sub_information = explode('-', $information[1]);
        foreach ($sub_information as $e) {
            $i = 0;
            if ($e !== "?") {
                $data[$information[0]] = [$sub_information[$i]];
            }
            $i .= 1;
        }

    } else {
        $data[$information[0]] = $information[1];
    }
}

if ($username === $data["nom"] && $mdp === $data["motdepasse"]) {
    $connexion = true;
} else {
    $connexion = false;
}


if (!$connexion) {
    echo "Utilisateur inconnu";
    exit();
} else {
    echo "Connecté";
    echo "<br>";
    echo "<br>";
}

$peugeot_checked = ""; // $peugeot_checked = "checked"
$citroen_checked = "";
$renault_checked = "";
$marque_checked = "";

$diesel_checked = ""; // $diesel_checked = "checked"
$essence_checked = "";
$carburant_checked = "";

$d1_selected = ""; // $d1_selected = "selected"
$d2_selected = "";
$d3_selected = "";
$d4_selected = "";
$e2_selected = "";
$e3_selected = "";
$e4_selected = "";
$moteur_selected = "";

$commentaire = "";

foreach ($data as $key => $value) {
    if ($key === "marque") {
        if (is_array($value)) {
            foreach ($value as $m) {
                if ($m === "peugeot") {
                    $peugeot_checked = "checked";
                }
                if ($m === "citroen") {
                    $citroen_checked = "checked";
                }
                if ($m === "renault") {
                    $renault_checked = "checked";
                }
                if ($m === "?") {
                    $marque_checked = "checked";
                }
            }
        }
    }
    if ($key === "carburant") {
        if ($value === "diesel") {
            $diesel_checked = "checked";
        }
        if ($value === "essence") {
            $essence_checked = "checked";
        }
        if ($value === "?") {
            $carburant_checked = "checked";
        }
    }
    if ($key === "moteur") {
        if ($value === "D1") {
            $d1_selected = "selected";
        }
        if ($value === "D2") {
            $d2_selected = "selected";
        }
        if ($value === "D3") {
            $d3_selected = "selected";
        }
        if ($value === "D4") {
            $d4_selected = "selected";
        }
        if ($value === "E2") {
            $e2_selected = "selected";
        }
        if ($value === "E3") {
            $e3_selected = "selected";
        }
        if ($value === "E4") {
            $e4_selected = "selected";
        }
        if ($value === "?") {
            $moteur_selected = "selected";
        }
    }
    if ($key === "commentaire") {
        $commentaire = $value;
    }
}


require_once 'afficher.html';
