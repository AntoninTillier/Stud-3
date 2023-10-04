package strategie;

import singleton.JournalisationSingleton;
import strategie.comportementCancan.Cancan;
import strategie.comportementVol.VolerAvecDesAiles;

public class Colvert extends Canard {
    public Colvert() {
        comportementCancan = new Cancan();
        comportementVol = new VolerAvecDesAiles();
    }

    public void afficher() {
        JournalisationSingleton.getInstance().journaliser("Je suis un Colvert");
    }
}