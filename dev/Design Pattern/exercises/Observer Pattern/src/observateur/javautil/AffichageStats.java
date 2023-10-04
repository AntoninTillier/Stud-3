package observateur.javautil;

import java.util.Observable;

public class AffichageStats implements java.util.Observer {
    private float maxTemp = 0.0f;
    private float minTemp = 200;
    private float somTemp = 0.0f;
    private int nbrLecture;
    private Observable observable;

    public AffichageStats(Observable observable) {
        this.observable = observable;
        observable.addObserver(this);
    }

    @Override
    public void update(Observable observable, Object o) {
        if (observable instanceof DonneesMeteo) {
            DonneesMeteo donneesMeteo = (DonneesMeteo) observable;
            somTemp += donneesMeteo.getTemperature();
            nbrLecture++;
            if (donneesMeteo.getTemperature() > maxTemp) {
                maxTemp = donneesMeteo.getTemperature();
            }
            if (donneesMeteo.getTemperature() < minTemp) {
                minTemp = donneesMeteo.getTemperature();
            }
            afficher();
        }
    }

    public void afficher() {
        System.out.println(
                "Affichage Stats Temperature Moy/Max/Min= " + (somTemp / nbrLecture) + "/" + maxTemp + "/" + minTemp);
    }
}