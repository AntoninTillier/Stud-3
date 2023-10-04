package decorateur.boisson.decorateurImpl;

import decorateur.boisson.Boisson;

public class Chocolat extends DecorateurIngredient {

    public Chocolat(Boisson boisson) {
        this.boisson = boisson;
    }

    public String getDescription() {
        return this.boisson.getDescription() + ",Chocolat";
    }

    public double getCout() {
        return this.boisson.getCout() + .20;
    }

}