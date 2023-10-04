package com.acdq.graph;

public class Arc {
    private Noeud source;
    private Noeud cible;
    private double poids;

    public Arc(Noeud x, Noeud y) {
        this.source = x;
        this.cible = y;
    }

    public Arc(Noeud x, Noeud y, double poids) {
        this.source = x;
        this.cible = y;
        this.poids = poids;
    }

    public String toString() {
        return "(" + source.toString() + "," + cible.toString() + ")[" + poids + "]";
    }

    public Noeud getSource() {
        return this.source;
    }

    public Noeud getCible() {
        return this.cible;
    }

    public double getPoids() {
        return this.poids;
    }
}
