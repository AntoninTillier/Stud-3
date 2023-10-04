package observateur.javautil;

public class SimulateurStationMeteo {

    public static void main(String[] args) {
        DonneesMeteo donneesMeteo = new DonneesMeteo();
        AffichageConditions affichageCond = new AffichageConditions(donneesMeteo);
        AffichageStats affichageStats = new AffichageStats(donneesMeteo);
        AffichagePrevisions affichagePrevisions = new AffichagePrevisions(donneesMeteo);
        System.out.println("************************************************************");
        donneesMeteo.setMesures(26, 65, 1020);
        System.out.println("************************ NOUVELLES DONNEES *******************");
        donneesMeteo.setMesures(28, 70, 1012);
        System.out.println("************************ NOUVELLES DONNEES *******************");
        donneesMeteo.setMesures(22, 90, 1012);
    }
}