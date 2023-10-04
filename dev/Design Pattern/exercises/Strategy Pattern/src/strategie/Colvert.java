package strategie;

import strategie.comportementCancan.Cancan;
import strategie.comportementCancan.Coincoin;
import strategie.comportementVol.NePasVoler;
import strategie.comportementVol.VolerAvecDesAiles;

public class Colvert extends Canard {

    public Colvert() {
        this.comportmentVol = new VolerAvecDesAiles();
        this.comportementCancan = new Cancan();
    }

    public void afficher() {
        System.out.println("colvert");
    }
}