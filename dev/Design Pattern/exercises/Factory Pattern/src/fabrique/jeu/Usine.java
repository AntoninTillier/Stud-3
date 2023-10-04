package fabrique.jeu;

// Usine abstraite qui sert de base aux usines concrètes.
public abstract class Usine {

    public Unite formerUnite(TypeUnite type) {
        Unite unite = this.creerUnite(type);
        unite.equiper();
        return unite;
    }
    // La création d'une unité est déléguée aux sous classes.
    public abstract Unite creerUnite(TypeUnite type);
}