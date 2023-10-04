package fabrique.jeu;

import java.util.ArrayList;


public abstract class Unite {
    protected String nom;
    protected int coutConstruction;
    protected int precisionAttaque;
    protected int esquiveDefense;
    protected ArrayList<String> equipements;

    // Méthode abstraite qui permet d'équiper l'unité.
    public abstract void equiper();

    // Méthode générique pour l'affichage de l'unité.
    public String toString() {
        String str = "Nom : " + this.nom + "\n";
        str += "Coût de construction : " + this.coutConstruction + "\n";
        str += "Précision d'attaque : " + this.precisionAttaque + "\n";
        str += "Esquive en défense : " + this.esquiveDefense + "\n";
        str += "Equipements : ";
        for (int i = 0; i < this.equipements.size(); i++) {
            str += this.equipements.get(i) + " ";
        }
        return str;
    }
}