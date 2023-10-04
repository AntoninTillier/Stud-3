package com.acdq.graph.generateur;

import com.acdq.graph.Graph;
import com.acdq.graph.Noeud;

public class GenerateurGraphComplet {

    public GenerateurGraphComplet(Graph g) {
        for (int i = 1; i < 100 + 1; i++) {
            g.getHmap().put(i, new Noeud(i));
        }
        for (int i = 1; i < 100 + 1; i++) {
            Noeud n = g.getNoeud(i);
            for (int j = 1; j < 100 + 1; j++) {
                Noeud nn = g.getNoeud(j);
                double a = (int) (Math.random() * 99);
                g.addArc(n.getId(), nn.getId(), a);
            }
        }
        System.gc();
    }
}