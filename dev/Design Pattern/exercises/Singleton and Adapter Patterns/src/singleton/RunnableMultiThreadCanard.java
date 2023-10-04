package singleton;

import fabrique.canard.FabriqueDeCanards;
import fabrique.canard.FabriqueDeCanardsAbstraite;
import strategie.CanardInterface;

public class RunnableMultiThreadCanard implements Runnable {

    @Override
    public void run() {
        FabriqueDeCanardsAbstraite fabriqueDeCanards = new FabriqueDeCanards();
        CanardInterface colvert1 = fabriqueDeCanards.creerCanard("colvert");
        colvert1.afficher();
        CanardInterface canardEnPlastique = fabriqueDeCanards.creerCanard("canardEnPlastique");
        canardEnPlastique.afficher();
    }
}