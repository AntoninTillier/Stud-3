package fabrique.jeu;

import java.util.ArrayList;

//Classe représentant un commandant extraterrestre.
public class CommandantExtraterrestre extends Unite {
    // Constructeur pour un commandant extraterrestre.
    public CommandantExtraterrestre() {
        this.nom = "Prédateur";
        this.coutConstruction = 10;
        this.precisionAttaque = 3;
        this.esquiveDefense = 3;
        this.equipements = new ArrayList<String>();
    }
    // Equiper un commandant extraterrestre.
    public void equiper() {
        this.equipements.add("Mitraillette à plasma");
        this.equipements.add("Peau");
        System.out.println("Equipement d'un commandant extraterrestre (Mitraillette à plasma, Peau).");
    }
}
