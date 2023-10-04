package observateur.stationmeteo;

public interface Observateur {
    public void actualiser(float temp, float humidite, float pression);
}