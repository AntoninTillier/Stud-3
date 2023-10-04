package strategie;

import strategie.comportementVol.ComportementVol;
import strategie.comportementVol.NePasVoler;

public class SimulateurCanard {

    public static void main(String[] args) {
        Canard c = new Colvert();
        c.afficher();
        c.effectuerVol();
        c.effectuerCancan();
        c.nager();
        System.out.println("--------------");
        Canard cc = new Colvert();
        cc.setComportementVol(new NePasVoler());
        cc.afficher();
        cc.effectuerVol();
        cc.effectuerCancan();
        cc.nager();
    }
}