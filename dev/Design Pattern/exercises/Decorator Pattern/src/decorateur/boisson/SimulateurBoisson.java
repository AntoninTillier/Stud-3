package decorateur.boisson;

import decorateur.boisson.decorateurImpl.Chantilly;
import decorateur.boisson.decorateurImpl.Chocolat;
import decorateur.boisson.decorateurImpl.Lait;

public class SimulateurBoisson {

    public static void main(String[] args) {
        Boisson expresso = new Espresso();
        expresso = new Chocolat(expresso);
        expresso = new Chocolat(expresso);
        expresso = new Chantilly(expresso);
        System.out.println(expresso.getDescription() + " € " + expresso.getCout());
        System.out.println("-----------------------------------------------------");
        Boisson colombia = new Colombia();
        colombia = new Lait(colombia);
        System.out.println(colombia.getDescription() + " € " + colombia.getCout());
    }
}