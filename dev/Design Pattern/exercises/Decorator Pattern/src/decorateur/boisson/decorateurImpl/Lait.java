package decorateur.boisson.decorateurImpl;

import decorateur.boisson.Boisson;

public class Lait extends DecorateurIngredient {

    public Lait(Boisson boisson) {
        this.boisson = boisson;
    }

    public String getDescription() {
        return this.boisson.getDescription() + ",Lait";
    }

    public double getCout() {
        return this.boisson.getCout() + .10;
    }

}