package strategie;

import strategie.comportementCancan.ComportementCancan;
import strategie.comportementVol.ComportementVol;

public abstract class Canard implements CanardInterface {
    ComportementVol comportementVol;
    ComportementCancan comportementCancan;

    public Canard() { }

    public abstract void afficher();

    public void effectuerVol() {
        comportementVol.voler();
    }

    public void effectuerCancan() {
        comportementCancan.cancaner();
    }

    public void nager() {
        System.out.println("Tous les canards ﬂottent, même les leurres!");
    }

    public void setComportementVol(ComportementVol cv) {
        comportementVol = cv;
    }

    public void setComportementCancan(ComportementCancan cc) {
        comportementCancan = cc;
    }
}