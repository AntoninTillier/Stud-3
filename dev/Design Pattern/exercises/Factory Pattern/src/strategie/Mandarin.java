package strategie;

import strategie.comportementCancan.Cancan;
import strategie.comportementVol.VolerAvecDesAiles;

public class Mandarin extends Canard {
    public Mandarin() {
        comportementCancan = new Cancan();
        comportementVol = new VolerAvecDesAiles();
    }

    public void afficher() {
        System.out.println("Je suis un Mandarin");
    }
}