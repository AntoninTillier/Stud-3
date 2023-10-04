package strategie;

import strategie.comportementCancan.CancanMuet;
import strategie.comportementVol.NePasVoler;

public class Leurre extends Canard {
    public Leurre() {
        comportementCancan = new CancanMuet();
        comportementVol = new NePasVoler();
    }

    public void afficher() {
        System.out.println("Je suis un Leurre");
    }
}