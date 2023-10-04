package com.aq.game.individu;

public class UsineHumain extends Usine {
    public Individu creerIndividu(TypeIndividu type) {
        Individu Individu = null;
        switch (type) {
            case JOUEUR:
                Individu = new Joueur();
                break;
            case MEDECIN:
                Individu = new Medecin(0, 0);
                break;
            case CUISINIER:
                Individu = new Cuisinier(0, 0);
                break;
        }
        return Individu;
    }
}