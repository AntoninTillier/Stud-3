package fabrique.canard;

import strategie.CanardInterface;

public abstract class FabriqueDeCanardsAbstraite {
    public abstract CanardInterface creerCanard(String nom);
}