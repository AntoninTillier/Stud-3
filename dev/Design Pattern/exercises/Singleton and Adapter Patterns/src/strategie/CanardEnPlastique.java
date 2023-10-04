package strategie;

import singleton.JournalisationSingleton;
import strategie.comportementCancan.Coincoin;
import strategie.comportementVol.NePasVoler;

public class CanardEnPlastique extends Canard {
    public CanardEnPlastique() {
        comportementCancan = new Coincoin();
        comportementVol = new NePasVoler();
    }

    public void afficher() {
        JournalisationSingleton.getInstance().journaliser("Je suis un canard en plastique");
    }
}