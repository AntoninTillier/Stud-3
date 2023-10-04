package com.acdq.graph.generateur;

import com.acdq.graph.Graph;
import com.acdq.graph.Noeud;

public class GenerateurGraph {

    public GenerateurGraph(Graph g) {
        double p = 1 + Math.random() * 100;
        for (int i = 1; i < 100 + 1; i++) {
            g.getHmap().put(i, new Noeud(i));
        }
        double r = Math.random();
        int v = 100;
        int w = 1 + (int) Math.floor((Math.log10((1 - r)) / (Math.log10(1 - p))));
        if (w != v && v < 100 + 1) {
            g.addArc(v, w);
            g.addArc(w, v);
        }
        w = 0;
        for (int i = 1; i < 100 + 1; i++) {
            r = i * Math.random();
            w += 1 + (int) Math.floor((Math.log10((1 - r)) / (Math.log10(1 - p))));
            v = (int) Math.floor(Math.random() * (100 + 1));
            if (v == 0)
                v = 100;
            if (w != v && v < 100 + 1) {
                int a = (int) (Math.random() * 99);
                g.addArc(v, w, a);
                g.addArc(w, v, a);
            }
        }
        System.gc();
    }
}