package fabrique.jeu;

import java.util.ArrayList;


public class CommandantHumain extends Unite {

    public CommandantHumain() {
        this.nom = "Lieutenant";
        this.coutConstruction = 14;
        this.precisionAttaque = 5;
        this.esquiveDefense = 2;
        this.equipements = new ArrayList<String>();
    }

    public void equiper() {
        this.equipements.add("Pistolet mitrailleur");
        this.equipements.add("Bouclier");
        System.out.println("Equipement d'un commandant humain (Pistolet mitrailleur, Bouclier).");
    }
}