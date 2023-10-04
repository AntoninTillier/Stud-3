package strategie;

import strategie.comportementVol.NePasVoler;
import strategie.comportementVol.PropulsionAReaction;

public class SimulateurCanard {

    public static void main(String[] args) {
        System.out.println();
        System.out.println(" ****** Début simulation comportement Canard ******");
        CanardInterface colvert1 = new Colvert();
        colvert1.afficher();
        colvert1.effectuerCancan();
        colvert1.effectuerVol();
        System.out.println(" ****** Fin simulation comportement Canard ******");
        System.out.println();
        System.out.println();
        System.out.println(" ****** Début simulation comportement Canard ******");
        System.out.println("Au debut :");
        CanardInterface colvert2 = new Colvert();
        colvert2.afficher();
        colvert2.effectuerCancan();
        colvert2.effectuerVol();
        System.out.println("Finalement :");
        colvert2.setComportementVol(new NePasVoler());
        colvert2.effectuerVol();
        System.out.println(" ****** Fin simulation comportement Canard ******");
        System.out.println();
        System.out.println();
        System.out.println(" ****** Début simulation comportement Canard ******");
        System.out.println("Au debut :");
        CanardInterface canardEnPlastique = new CanardEnPlastique();
        canardEnPlastique.afficher();
        canardEnPlastique.effectuerCancan();
        canardEnPlastique.effectuerVol();
        System.out.println("Finalement, merci la technologie :");
        canardEnPlastique.setComportementVol(new PropulsionAReaction());
        canardEnPlastique.effectuerVol();
        System.out.println(" ****** Fin simulation comportement Canard ******");
        System.out.println();
    }
}