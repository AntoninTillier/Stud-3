package observateur.javautil;

import java.util.Observable;

public class AffichagePrevisions implements java.util.Observer {
    private float actuellePression = 29.92f;
    private float anciennePression;
    private Observable observable;

    public AffichagePrevisions(Observable observable) {
        this.observable = observable;
        observable.addObserver(this);
    }

    @Override
    public void update(Observable observable, Object o) {
        if (observable instanceof DonneesMeteo) {
            DonneesMeteo donneesMeteo = (DonneesMeteo) observable;
            anciennePression = actuellePression;
            actuellePression = donneesMeteo.getPression();
            afficher();
        }
    }

    public void afficher() {
        System.out.print("Affichage previsions : ");
        if (actuellePression > anciennePression) {
            System.out.println("Amélioration de la météo");
        } else if (actuellePression == anciennePression) {
            System.out.println("Pas de changement");
        } else if (actuellePression < anciennePression) {
            System.out.println("Temps frais et pluvieux");
        }
    }
}