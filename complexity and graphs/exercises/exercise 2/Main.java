package TP2;

import TP1.Graph;

public class Main {
    public static void main(String[] args) {
        Graph g = new Graph();
        g.addNoeud(1);
        g.addNoeud(2);
        g.addNoeud(3);
        g.addNoeud(4);
        g.addNoeud(5);
        g.addNoeud(6);
        g.addNoeud(7);
        g.addNoeud(8);
        g.addNoeud(9);
        System.out.println(g.toString());
        System.out.println("----------------------------");
        g.addArc(1, 3);
        g.addArc(1, 5);
        g.addArc(1, 6);
        g.addArc(1, 7);
        g.addArc(2, 1);
        g.addArc(3, 2);
        g.addArc(3, 6);
        g.addArc(4, 2);
        g.addArc(4, 9);
        g.addArc(7, 3);
        g.addArc(7, 6);
        g.addArc(8, 2);
        g.addArc(8, 4);
        g.addArc(8, 5);
        System.out.println(g.toString());
        System.out.println("----------------------------");
        g.parcours();
        System.out.println("----------------------------");
        g.profI();
        System.out.println("----------------------------");
        g.largeur();
    }
}