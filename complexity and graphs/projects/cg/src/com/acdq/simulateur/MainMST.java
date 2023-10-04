package com.acdq.simulateur;

import com.acdq.graph.Graph;
import com.acdq.graph.generateur.GenerateurGraph;
import com.acdq.mst.MinimumSpanningTree;
import com.acdq.performance.Fichier;
import com.acdq.performance.SystemInfo;
import com.acdq.performance.TimeRun;

public class MainMST {

    public static void main(String[] args) {
        System.out.println("Run");
        StringBuilder res = new StringBuilder();
        SystemInfo systemInfo = new SystemInfo();
        res.append(systemInfo.getInfo());
        res.append("\n");
        res.append("\n");
        TimeRun tr = new TimeRun();
        for (int i = 0; i < 1000; i++) {
            Graph g = new Graph();
            GenerateurGraph gg = new GenerateurGraph(g);
            res.append("taille du graph : " + g.getHmap().size() + "\n");
            res.append(g);
            res.append("\n");
            tr.setStartTime(System.currentTimeMillis());
            MinimumSpanningTree mst = new MinimumSpanningTree(g);
            mst.init();
            tr.setStopTime(System.currentTimeMillis());
            res.append("La solution de l'agorithme Minimum Spanning Tree : " + mst.getCoutCircuit());
            res.append("\n");
            res.append("\n");
            tr.add();
        }
        res.append("Temps d'execution total : " + tr.getTotal());
        res.append("\n");
        res.append("L'average du temps d'execution : " + tr.getAverage() + " milliseconde");
        res.append("\n");
        res.append("L'ecart-type du temps d'execution : " + tr.getStandardDeviation() + " milliseconde");
        Fichier f = new Fichier(res, "MinimumSpanningTree");
        System.out.println("--------");
        System.out.println("End");

    }
}