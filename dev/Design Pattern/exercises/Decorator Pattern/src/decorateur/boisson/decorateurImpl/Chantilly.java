package decorateur.boisson.decorateurImpl;

import decorateur.boisson.Boisson;

public class Chantilly extends DecorateurIngredient {

    public Chantilly(Boisson boisson) {
        this.boisson = boisson;
    }

    public String getDescription() {
        return this.boisson.getDescription() + ",Chantilly";
    }

    public double getCout() {
        return this.boisson.getCout() + .10;
    }

}