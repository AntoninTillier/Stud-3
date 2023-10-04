package strategie;

import strategie.comportementCancan.ComportementCancan;
import strategie.comportementVol.ComportementVol;

public interface CanardInterface {

    public void afficher();

    public void effectuerVol();

    public void effectuerCancan();

    public void nager();

    public void setComportementVol(ComportementVol cv);

    public void setComportementCancan(ComportementCancan cc);
}