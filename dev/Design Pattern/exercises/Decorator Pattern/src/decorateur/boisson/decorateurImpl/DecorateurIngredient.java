package decorateur.boisson.decorateurImpl;

import decorateur.boisson.Boisson;

public abstract class DecorateurIngredient extends Boisson {
    Boisson boisson;

    public String getDescription() {
        return this.boisson.getDescription();
    }

    public double getCout() {
        return this.boisson.getCout();
    }

}