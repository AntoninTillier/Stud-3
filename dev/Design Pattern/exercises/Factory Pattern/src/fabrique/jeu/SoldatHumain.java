package fabrique.jeu;

import java.util.ArrayList;

// Classe représentant un soldat humain.
public class SoldatHumain extends Unite {
    // Constructeur pour un soldat humain.
    public SoldatHumain() {
        this.nom = "Fantassin";
        this.coutConstruction = 5;
        this.precisionAttaque = 1;
        this.esquiveDefense = 2;
        this.equipements = new ArrayList<String>();
    }
    // Equiper un soldat humain.
    public void equiper() {
        this.equipements.add("Pistoler");
        this.equipements.add("Bouclier");
        System.out.println("Equipement d'un soldat humain (Pistoler, Bouclier).");
    }
}