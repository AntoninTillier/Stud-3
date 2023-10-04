package TP4;

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
        g.addArc(1, 2, 10);
        g.addArc(1, 3, 3);
        g.addArc(1, 5, 6);
        g.addArc(2, 1, 0);
        g.addArc(3, 2, 4);
        g.addArc(3, 5, 2);
        g.addArc(4, 3, 4);
        g.addArc(4, 5, 3);
        g.addArc(5, 2, 0);
        g.addArc(5, 6, 1);
        g.addArc(6, 1, 2);
        g.addArc(6, 2, 1);
        System.out.println(g.toString());
        System.out.println("----------------------------");
        Dijkstra dijKstra = new Dijkstra(g);
        dijKstra.init(g.getNoeud(1));
        System.out.println(dijKstra.toString());
        System.out.println("----------------------------");
        dijKstra.compute(g.getNoeud(1));
    }
}