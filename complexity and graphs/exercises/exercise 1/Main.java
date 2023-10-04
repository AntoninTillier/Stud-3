package TP1;

public class Main {
    public static void main(String[] args) {
        Graph g = new Graph(4);
        System.out.println(g.toString());
        System.out.println("----------------------------");
        g.addNoeud(2);
        g.addNoeud(5);
        System.out.println(g.toString());
        System.out.println("----------------------------");
        g.addArc(1, 2);
        g.addArc(1, 3);
        g.addArc(4, 3);
        System.out.println(g.toString());
        System.out.println("----------------------------");
        Graph g2 = new Graph("graphe.csv");
        System.out.println(g2.toString());
    }
}