package fabrique.canard;

import strategie.CanardEnPlastique;
import strategie.CanardInterface;
import strategie.Colvert;
import strategie.Mandarin;

public class FabriqueDeCanards extends FabriqueDeCanardsAbstraite {

    @Override
    public CanardInterface creerCanard(String nom) {
        CanardInterface canard = null;
        switch (nom) {
            case "colvert":
                canard = new Colvert();
                break;
            case "mandarin":
                canard = new Mandarin();
                break;
            case "canardEnPlastique":
                canard = new CanardEnPlastique();
                break;
        }
        return canard;
    }
}