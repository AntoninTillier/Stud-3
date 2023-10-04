package com.aq.game.individu;

public abstract class Usine {

    public Individu formerIndividu(TypeIndividu type) {
        Individu Individu = this.creerIndividu(type);
        return Individu;
    }

    public abstract Individu creerIndividu(TypeIndividu type);
}