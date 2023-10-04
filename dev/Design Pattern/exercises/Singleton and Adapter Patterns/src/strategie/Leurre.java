package strategie;

import singleton.JournalisationSingleton;
import strategie.comportementCancan.CancanMuet;
import strategie.comportementVol.NePasVoler;

public class Leurre extends Canard {
    public Leurre() {
        comportementCancan = new CancanMuet();
        comportementVol = new NePasVoler();
    }

    public void afficher() {
        JournalisationSingleton.getInstance().journaliser("Je suis un leurre");
    }
}