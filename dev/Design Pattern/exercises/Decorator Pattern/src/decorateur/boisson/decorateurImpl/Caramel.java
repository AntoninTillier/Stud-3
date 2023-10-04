package decorateur.boisson.decorateurImpl;

import decorateur.boisson.Boisson;

public class Caramel extends DecorateurIngredient {

    public Caramel(Boisson boisson) {
        this.boisson = boisson;
    }

    public String getDescription() {
        return this.boisson.getDescription() + ",Caramel";
    }

    public double getCout() {
        return this.boisson.getCout() + .15;
    }

}