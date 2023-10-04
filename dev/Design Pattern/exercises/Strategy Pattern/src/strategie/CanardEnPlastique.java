package strategie;

import strategie.comportementCancan.CancanMuet;
import strategie.comportementCancan.Coincoin;
import strategie.comportementVol.NePasVoler;

public class CanardEnPlastique extends Canard {

    public CanardEnPlastique() {
        this.comportmentVol = new NePasVoler();
        this.comportementCancan = new Coincoin();
    }

    public void afficher() {
        System.out.println("Canard en plastique");
    }
}