package com.acdq.nn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

import com.acdq.graph.Arc;
import com.acdq.graph.Graph;
import com.acdq.graph.Noeud;

public class NearestNeighbour {
    private Graph graph;
    private ArrayList<Integer> res;

    public NearestNeighbour(Graph g) {
        this.graph = g;
        this.res = new ArrayList<Integer>();
    }

    public void init(Noeud src) {
        HashMap<Integer, PCC> encours = new HashMap<Integer, PCC>();
        this.res.add(src.getId());
        this.graph.getNoeud(src.getId()).setMark(true);
        for (int i : this.graph.getHmap().keySet()) {
            Noeud n = this.graph.getHmap().get(i);
            if (src.getId() != n.getId())
                encours.put(i, new PCC(n, src));
        }
        double min = Double.POSITIVE_INFINITY;
        PCC p = null;
        for (int i : encours.keySet()) {
            if (encours.get(i).getDistance() < min) {
                p = encours.get(i);
                if (!p.getCible().getMark()) {
                    min = encours.get(i).getDistance();
                } else {
                    p = null;
                }
            }
        }
        if (p != null) {
            encours = null;
            System.gc();
            init(p.getCible());
        } else {
            System.gc();
        }
    }

    public String toString() {
        return res.toString();
    }
}