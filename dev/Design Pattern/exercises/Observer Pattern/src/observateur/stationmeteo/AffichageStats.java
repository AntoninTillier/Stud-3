package observateur.stationmeteo;

public class AffichageStats implements Observateur {
    private float maxTemp = 0.0f;
    private float minTemp = 200;
    private float somTemp = 0.0f;
    private int nbrLecture;
    private Sujet donneesMeteo;

    public AffichageStats(Sujet donneesMeteo) {
        this.donneesMeteo = donneesMeteo;
        donneesMeteo.enregistrerObservateur(this);
    }

    public void actualiser(float temperature, float humidite, float pression) {
        somTemp += temperature;
        nbrLecture++;
        if (temperature > maxTemp) {
            maxTemp = temperature;
        }
        if (temperature < minTemp) {
            minTemp = temperature;
        }
        afficher();
    }

    public void afficher() {
        System.out.println(
                "Affichage Stats Temperature Moy/Max/Min= " + (somTemp / nbrLecture) + "/" + maxTemp + "/" + minTemp);
    }
}