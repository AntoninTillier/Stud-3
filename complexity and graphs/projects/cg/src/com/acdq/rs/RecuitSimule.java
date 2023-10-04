package com.acdq.rs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import com.acdq.graph.Graph;
import com.acdq.graph.Noeud;
import com.acdq.nn.PCC;

public class RecuitSimule {
    private Graph g;
    private ArrayList<Integer> L; // contient la liste du parcours de villes à effectuer
    private Double T; // la température initiale du système
    private HashMap<Double, ArrayList<Integer>> toutSoluce = new HashMap<Double, ArrayList<Integer>>();

    public RecuitSimule(Graph grph) {
        g = grph;
    }

    public void init() {
        L = new ArrayList<Integer>();
        for (int i : g.getHmap().keySet()) {
            L.add(i);
        }
        this.compute();
    }

    public void compute() {
        // L'<- Générer-trajet-initial S
        this.T = (double) L.size(); // T <- L'
        Double p = null;
        ArrayList<Integer> L2 = L;
        int i = 0;
        while (T > 0.00001) {// tant que T>T0 faire
            for (int k = 0; k < L.size() + 1; k++) {
                L2 = transformation(i);// L"<-transformation L'
                i++;
                if (trajet2(L2) < trajet2(L)) {// si Trajet L" < Trajet L' alors
                    L = L2;// L'<-L"
                    toutSoluce.put((double) k, L);
                } else {// sinon
                    toutSoluce.put((double) k, L);
                    p = Math.random(); // Tirage d'un nombre p∈[0;1]
                    if (p > Math.exp((trajet2(L2) - trajet2(L)) / T)) {// si p>e^((Trajet L"-Trajet L')/T) alors
                        L = L2;// L'<-L"
                        toutSoluce.put((double) k, L);
                    }
                }
                T = 0.999 * T;
                if (i == L2.size() - 1)
                    i = 0;
            }
            if (L.size() < T)// si L'<T alors
                T = (double) L.size();// T<-L'
        }
    }

    public String soluce() {
        String s = "";
        double min = Double.POSITIVE_INFINITY;
        for (Double i : toutSoluce.keySet()) {
            double res = trajet2(toutSoluce.get(i));
            if (res < min) {
                min = res;
                s = "min : " + toutSoluce.get(min).toString();
            }
        }
        return s;
    }

    public ArrayList<Integer> transformation(int i) {
        ArrayList<Integer> L2 = L;
        Collections.swap(L2, i, i + 1);
        return L2;
    }

    public Double trajet2(ArrayList<Integer> L) {
        double res = 0;
        for (int i = 0; i < L.size(); i++) {
            Noeud n = g.getNoeud(L.get(i));
            Noeud nn;
            if (i + 1 < L.size() - 1)
                nn = g.getNoeud(L.get(i + 1));
            else
                nn = g.getNoeud(L.get(0));
            if (n.hasSuccesseur(i))
                res += n.distArc(nn);
            else {
                res = Double.POSITIVE_INFINITY;
                return res;
            }
        }
        return res;
    }

    public String toString() {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < L.size(); i++) {
            if (i < L.size() - 1)
                res.append(this.L.get(i) + "->");
            else
                res.append(this.L.get(i));
        }
        return res.toString();
    }
}