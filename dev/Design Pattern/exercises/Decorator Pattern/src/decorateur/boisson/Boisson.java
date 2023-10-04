package decorateur.boisson;

public abstract class Boisson {
    String description;
    double cout;

    public String getDescription() {
        return this.description;
    }

    public double getCout() {
        return this.cout;
    }
}