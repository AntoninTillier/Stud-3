package fabrique.jeu;


public class UsineExtraterrestre extends Usine {

    public Unite creerUnite(TypeUnite type) {
        Unite unite = null;
        switch (type) {
            case SOLDAT:
                unite = new SoldatExtraterrestre();
                break;
            case COMMANDANT:
                unite = new CommandantExtraterrestre();
                break;
        }
        return unite;
    }
}