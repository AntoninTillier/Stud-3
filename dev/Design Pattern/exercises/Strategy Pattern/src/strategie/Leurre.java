package strategie;

import strategie.comportementCancan.Cancan;
import strategie.comportementCancan.CancanMuet;
import strategie.comportementVol.NePasVoler;
import strategie.comportementVol.VolerAvecDesAiles;

public class Leurre extends Canard {

    public Leurre() {
        this.comportmentVol = new NePasVoler();
        this.comportementCancan = new CancanMuet();
    }

    public void afficher() {
        System.out.println("leurre");
    }
}