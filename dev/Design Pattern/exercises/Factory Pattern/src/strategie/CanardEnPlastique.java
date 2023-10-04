package strategie;

import strategie.comportementCancan.Coincoin;
import strategie.comportementVol.NePasVoler;

public class CanardEnPlastique extends Canard {
    public CanardEnPlastique() {
        comportementCancan = new Coincoin();
        comportementVol = new NePasVoler();
    }

    public void afficher() {
        System.out.println("Je suis un canard en plastique");
    }
}