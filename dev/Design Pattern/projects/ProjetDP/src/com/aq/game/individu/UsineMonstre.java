package com.aq.game.individu;

public class UsineMonstre extends Usine {
    public Individu creerIndividu(TypeIndividu type) {
        Individu Individu = null;
        switch (type) {
            case MONSTRE:
                Individu = new Monstre(0, 0);
                break;
            case SAGE:
                Individu = new Sage(0, 0);
                break;
            case MINIBOSS:
                Individu = new MiniBoss(0, 0);
                break;
            case BOSS:
                Individu = new Boss(0, 0);
                break;
        }
        return Individu;
    }
}