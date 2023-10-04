package TP4;

import TP1.Noeud;

public class PCC {
    private Noeud cible;
    private Noeud predecesseur;
    private double distance;

    public PCC(Noeud cible, Noeud predecesseur) {
        this.setCible(cible);
        this.setPredecesseur(predecesseur);
        if (cible.getId() == predecesseur.getId()) this.setDistance(0);
        else if (predecesseur.hasSuccesseur(cible.getId())) this.setDistance(predecesseur.distArc(cible));
        else this.setDistance(Double.POSITIVE_INFINITY);
    }

    public Noeud getCible() {
        return cible;
    }

    public void setCible(Noeud cible) {
        this.cible = cible;
    }

    public Noeud getPredecesseur() {
        return predecesseur;
    }

    public void setPredecesseur(Noeud predecesseur) {
        this.predecesseur = predecesseur;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public String toString() {
        return "Plus court chemin du noeud " + this.predecesseur.getId() + " vers le noeud " + this.cible.getId() + " : " + this.distance;
    }
}