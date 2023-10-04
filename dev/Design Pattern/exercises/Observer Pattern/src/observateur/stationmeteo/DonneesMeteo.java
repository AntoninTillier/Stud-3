package observateur.stationmeteo;

import java.util.ArrayList;

public class DonneesMeteo implements Sujet {
    private ArrayList observateurs = new ArrayList();
    private float temperature;
    private float humidite;
    private float pression;

    @Override
    public void enregistrerObservateur(Observateur o) {
        observateurs.add(o);
    }

    @Override
    public void supprimerObservateur(Observateur o) {
        int i = observateurs.indexOf(o);
        if (i >= 0) {
            observateurs.remove(i);
        }
    }

    @Override
    public void notiferObservateurs() {
        for (int i = 0; i < observateurs.size(); i++) {
            Observateur observer = (Observateur) observateurs.get(i);
            observer.actualiser(temperature, humidite, pression);
        }
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
}