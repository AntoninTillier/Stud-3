package strategie;

import strategie.comportementCancan.Cancan;
import strategie.comportementVol.VolerAvecDesAiles;

public class Mandarin extends Canard {

    public Mandarin() {
        this.comportmentVol = new VolerAvecDesAiles();
        this.comportementCancan = new Cancan();
    }

    public void afficher() {
        System.out.println("mandarin");
    }
}