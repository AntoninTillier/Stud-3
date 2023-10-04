package observateur.javautil;

import java.util.ArrayList;

public class DonneesMeteo extends java.util.Observable {
    private float temperature;
    private float humidite;
    private float pression;

    public void notiferObservateurs() {
        setChanged();
        notifyObservers();
    }

    public void actualiserMesures() {
        notiferObservateurs();
    }

    public void setMesures(float temperature, float humidite, float pression) {
        this.temperature = temperature;
        this.humidite = humidite;
        this.pression = pression;
        actualiserMesures();
    }

    public float getTemperature() {
        return temperature;
    }

    public float getHumidite() {
        return humidite;
    }

    public float getPression() {
        return pression;
    }
}