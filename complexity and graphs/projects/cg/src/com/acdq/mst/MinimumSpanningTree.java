package com.acdq.mst;

import java.util.HashMap;
import java.util.LinkedList;

import com.acdq.graph.Arc;
import com.acdq.graph.Graph;
import com.acdq.graph.Noeud;

public class MinimumSpanningTree {
    private Graph graphe;
    private LinkedList<Arc> arcs; // le tableau des Arcs dans le circuit
    private HashMap<Integer, LinkedList<Arc>> arcRangee = new HashMap<Integer, LinkedList<Arc>>();
    // toutes les Arcs du graphe rangee en fonction de leur poids

    public MinimumSpanningTree(Graph g) {
        this.graphe = g;
    }

    public void init() { // initialise les attribut de MST
        arcs = new LinkedList<Arc>();
        arcsRangee();
        for (int i : arcRangee.keySet()) {
            if (arcs.size() < graphe.getHmap().size() - 1) {
                for (Arc a : arcRangee.get(i)) {
                    if (arcs.size() < graphe.getHmap().size()) {
                        addArc(a);
                    } else {
                        break;
                    }
                }
            } else {
                break;
            }
        }
    }

    private void addArc(Arc a) { // ajoute l'arc 'a' dans le circuit 'arcs' si ce dernier ne cree pas de circuit
        if (this.arcs == null) {
            this.arcs.add(a);
        } else {
            boolean x = false, y = false;
            for (Arc arc : this.arcs) {
                if (arc.getCible() == a.getCible() || arc.getSource() == a.getCible()) {
                    x = true;
                }
                if (arc.getCible() == a.getSource() || arc.getSource() == a.getSource()) {
                    y = true;
                }
            }
            if (!(x & y)) {
                this.arcs.add(a);
            } else {
                LinkedList<Arc> n = new LinkedList<Arc>();
                n.addAll(this.arcs);
                n.add(a);
                if (!isCycle(n)) {
                    this.arcs.add(a);
                }
            }
        }
    }

    private boolean isCycle(LinkedList<Arc> arc) { // verifie si le circuit 'arc' est un cycle ou non
        HashMap<Integer, LinkedList<Noeud>> n = new HashMap<Integer, LinkedList<Noeud>>();
        for (int i = 0; i < arc.size(); i++) {
            n.put(i, new LinkedList<Noeud>());
        }
        for (Arc a : arc) {
            for (int i : n.keySet()) {
                if (n.get(i).isEmpty()) {
                    n.get(i).add(a.getCible());
                    n.get(i).add(a.getSource());
                    break;
                } else {
                    boolean test = false;
                    if (n.get(i).contains(a.getCible()) && n.get(i).contains(a.getSource())) {
                        return true;
                    }
                    if (n.get(i).contains(a.getCible()) && !n.get(i).contains(a.getSource())) {
                        n.get(i).add(a.getSource());
                        test = true;
                    }
                    if (!n.get(i).contains(a.getCible()) && n.get(i).contains(a.getSource())) {
                        n.get(i).add(a.getCible());
                        test = true;
                    }
                    if (test) {
                        HashMap<Integer, LinkedList<Noeud>> m = n;
                        for (int j : n.keySet()) {
                            for (int k : m.keySet()) {
                                if (j != k) {
                                    for (Noeud p : m.get(k)) {
                                        if (n.get(j).contains(p)) {
                                            n.get(j).remove(p);
                                            n.get(j).addAll(n.get(k));
                                            n.put(k, new LinkedList<Noeud>());
                                        }
                                    }
                                }
                            }
                        }
                    }

                }

            }
        }
        return false;
    }

    private void arcsRangee() { // range les arcs en fonction de leur poids
        for (int i : this.graphe.getHmap().keySet()) {
            for (Arc a : this.graphe.getNoeud(i).getSucc()) {
                if (!contentArc(a)) {
                    this.addArcRangee(a);
                }
            }
        }
    }

    private boolean contentArc(Arc arc) { // verifie si un arc est contenu dans arcRangee
        if (this.arcRangee.get((int) arc.getPoids()) == null) {
            return false;
        } else {
            for (Arc a : this.arcRangee.get((int) arc.getPoids())) {
                if ((a.getCible() == arc.getCible() && a.getSource() == arc.getSource())
                        || (a.getCible() == arc.getSource() && a.getSource() == arc.getCible())) {
                    return true;
                } else {
                    continue;
                }
            }
            return false;
        }
    }

    private void addArcRangee(Arc a) { // Ajoute un arc dans arcRangee
        if (this.arcRangee.get((int) a.getPoids()) == null) {
            LinkedList<Arc> arc = new LinkedList<Arc>();
            arc.add(a);
            this.arcRangee.put((int) a.getPoids(), arc);
        } else {
            this.arcRangee.get((int) a.getPoids()).add(a);
        }
    }

    public LinkedList<Arc> getArcs() {
        return arcs;
    }

    public Graph getGraph() {
        return graphe;
    }

    public HashMap<Integer, LinkedList<Arc>> getArcRangee() {
        return arcRangee;
    }

    public double getCoutCircuit() {
        double d = 0;
        for (Arc a : arcs) {
            d += a.getPoids();
        }
        return d;
    }
}