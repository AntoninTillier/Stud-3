package strategie;

import strategie.comportementCancan.ComportementCancan;
import strategie.comportementVol.ComportementVol;

public abstract class Canard {
    ComportementVol comportmentVol;
    ComportementCancan comportementCancan;

    public Canard() { }

    public abstract void afficher();

    public void effectuerVol() {
        this.comportmentVol.voler();
    }

    public void effectuerCancan() {
        this.comportementCancan.cancaner();
    }

    public void setComportementVol(ComportementVol a) {
        this.comportmentVol = a;
    }

    public void setComportementCancan(ComportementCancan c) {
        this.comportementCancan = c;
    }

    public void nager() {
        System.out.println("nager");
    }
}