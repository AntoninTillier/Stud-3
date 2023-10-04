package observateur.javautil;

import java.util.Observable;

public class AffichageConditions implements java.util.Observer {
    private float temperature;
    private float humidite;
    private Observable observable;

    public AffichageConditions(Observable observable) {
        this.observable = observable;
        observable.addObserver(this);
    }

    @Override
    public void update(Observable observable, Object o) {
        if (observable instanceof DonneesMeteo) {
            DonneesMeteo donneesMeteo = (DonneesMeteo) observable;
            this.temperature = donneesMeteo.getTemperature();
            this.humidite = donneesMeteo.getHumidite();
            afficher();
        }
    }

    public void afficher() {
        System.out.println("Affichage Conditions : " + temperature + " degrés C et " + humidite + " % d humidité");
    }
}