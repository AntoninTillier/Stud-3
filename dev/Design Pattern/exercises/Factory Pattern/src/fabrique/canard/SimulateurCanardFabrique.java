package fabrique.canard;


import strategie.CanardInterface;
import strategie.comportementVol.NePasVoler;
import strategie.comportementVol.PropulsionAReaction;

public class SimulateurCanardFabrique {

    public static void main(String[] args) {
        FabriqueDeCanardsAbstraite fabriqueDeCanards = new FabriqueDeCanards();
        System.out.println();
        System.out.println(" ****** Début simulation comportement Canard colvert ******");
        CanardInterface colvert1 = fabriqueDeCanards.creerCanard("colvert");
        colvert1.afficher();
        colvert1.effectuerCancan();
        colvert1.effectuerVol();
        System.out.println(" ****** Fin simulation comportement Canard colvert ******");
        System.out.println();
        System.out.println();
        System.out.println(" ****** Début simulation comportement Canard colvert 2 ******");
        System.out.println("Au debut :");
        CanardInterface colvert2 = fabriqueDeCanards.creerCanard("colvert");
        colvert2.afficher();
        colvert2.effectuerCancan();
        colvert2.effectuerVol();
        System.out.println("Finalement :");
        colvert2.setComportementVol(new NePasVoler());
        colvert2.effectuerVol();
        System.out.println(" ****** Fin simulation comportement Canard colvert 2 ******");
        System.out.println();
        System.out.println();
        System.out.println(" ****** Début simulation comportement Canard en plastique ******");
        System.out.println("Au debut :");
        CanardInterface canardEnPlastique = fabriqueDeCanards.creerCanard("canardEnPlastique");
        canardEnPlastique.afficher();
        canardEnPlastique.effectuerCancan();
        canardEnPlastique.effectuerVol();
        System.out.println("Finalement, merci la technologie :");
        canardEnPlastique.setComportementVol(new PropulsionAReaction());
        canardEnPlastique.effectuerVol();
        System.out.println(" ****** Fin simulation comportement Canard en plastique ******");
        System.out.println();
    }
}