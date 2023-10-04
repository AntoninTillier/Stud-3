package TP4;

import java.util.HashMap;
import java.util.Optional;

import TP1.Arc;
import TP1.Graph;
import TP1.Noeud;

public class Dijkstra {
    private Graph graph;
    private Noeud source;
    private HashMap<Integer, PCC> encours;
    private HashMap<Integer, PCC> finaux;
    private String chemin = "";

    public Dijkstra(Graph g) {
        this.graph = g;
        this.source = null;
    }

    public void init(Noeud src) {
        this.source = src;
        this.encours = new HashMap<Integer, PCC>();
        this.finaux = new HashMap<Integer, PCC>();
        this.encours.clear();
        this.finaux.clear();
        this.chemin += src + ";";
        this.finaux.put(this.source.getId(), new PCC(this.source, this.source));
        for (int i : this.graph.getHmap().keySet()) {
            Noeud n = this.graph.getHmap().get(i);
            if (this.source.getId() == n.getId()) continue;
            else {
                this.encours.put(i, new PCC(n, this.source));
            }
        }
    }

    public void compute(Noeud src) {
        while (this.encours != null) {
            double min = Double.POSITIVE_INFINITY;
            PCC p = null;
            for (int i : this.encours.keySet()) {
                if (this.encours.get(i).getDistance() < min) {
                    min = encours.get(i).getDistance();
                    p = encours.get(i);
                }
            }
            if (p == null) {
                this.chemin = removeLastCharOptional(this.chemin);
                System.out.println(this.chemin);
                break;
            }
            Noeud n = p.getCible();
            this.chemin += n.toString() + ";";
            this.encours.remove(n.getId());
            this.finaux.put(n.getId(), p);
            double dist = p.getDistance();
            for (int i = 0; i < n.getSucc().size(); i++) {
                Arc a = n.getSucc().get(i);
                if (!finaux.containsKey(a.getCible().getId())) {
                    if (a.getPoids() + getFinDist(n) < encours.get(a.getCible().getId()).getDistance()) {
                        PCC p2 = new PCC(a.getCible(), n);
                        p2.setDistance(dist + a.getPoids());
                        this.encours.replace(a.getCible().getId(), p2);
                    }
                }
            }
            System.out.println("----------------------------");
            System.out.println(this.toString());
        }
    }

    public double getFinDist(Noeud n) {
        for (int i : this.finaux.keySet()) {
            if (this.finaux.get(i).getCible().getId() == n.getId()) return this.finaux.get(i).getDistance();
        }
        return 0;
    }

    public String toString() {
        String res = "En cours : \n";
        for (int i : this.encours.keySet()) {
            res += i + " : " + this.encours.get(i) + "\n";
        }
        res += "\nFinaux : \n";
        for (int i : this.finaux.keySet()) {
            res += i + " : " + this.finaux.get(i) + "\n";
        }
        return res;
    }

    public static String removeLastCharOptional(String s) {
        return Optional.ofNullable(s)
                .filter(str -> str.length() != 0)
                .map(str -> str.substring(0, str.length() - 1))
                .orElse(s);
    }
}