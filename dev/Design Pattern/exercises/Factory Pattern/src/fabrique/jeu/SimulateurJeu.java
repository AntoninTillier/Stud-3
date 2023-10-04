package fabrique.jeu;


public class SimulateurJeu {

    public static void main(String[] args) {
        Usine usineExtraterrestre = new UsineExtraterrestre();
        Unite unite = usineExtraterrestre.formerUnite(TypeUnite.COMMANDANT);
        System.out.println(unite);
    }
}