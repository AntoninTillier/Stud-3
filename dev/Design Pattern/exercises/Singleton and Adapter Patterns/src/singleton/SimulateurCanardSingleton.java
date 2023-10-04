package singleton;

import fabrique.canard.FabriqueDeCanards;
import fabrique.canard.FabriqueDeCanardsAbstraite;
import strategie.CanardInterface;


public class SimulateurCanardSingleton {

    public static void main(String[] args) {
        FabriqueDeCanardsAbstraite fabriqueDeCanards = new FabriqueDeCanards();
        CanardInterface colvert1 = fabriqueDeCanards.creerCanard("colvert");
        colvert1.afficher();
        CanardInterface colvert2 = fabriqueDeCanards.creerCanard("colvert");
        colvert2.afficher();
        CanardInterface canardEnPlastique = fabriqueDeCanards.creerCanard("canardEnPlastique");
        canardEnPlastique.afficher();
        System.out.println(JournalisationSingleton.getInstance().terminerJournal());
    }
}