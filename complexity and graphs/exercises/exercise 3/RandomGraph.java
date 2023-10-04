package TP3;

import TP1.Graph;
import TP1.Noeud;

public class RandomGraph extends Graph {

    public RandomGraph(int n, double p) {
        this.getHmap().clear();
        int v = 1;
        int w = -1;
        while (v < n) {
            double r = Math.random();
            w = w + 1 + (int) (Math.log(1 - r) / Math.log(1 - p));
            while (w >= v && v < n) {
                w = w - v;
                v = v + 1;
            }
            if (v < n) {
                addArcRandom(v, w);
                addArcRandom(w, v);
            }
        }
    }

    public RandomGraph(int n, int m) {
        this.getHmap().clear();
        for (int i = 0; i < n; i++) {
            this.getHmap().put(i, new Noeud(i));
        }
        int tab[];
        for (int i = 0; i < m - 1; i++) {
            do {
                int r = (int) (Math.random() * ((n - 1) * ((n - 1) - 1) / 2));
                tab = bijection(r);
            } while (this.getNoeud(tab[0]).hasSuccesseur(tab[1]));
            this.addArc(tab[0], tab[1]);
            this.addArc(tab[1], tab[0]);
        }
    }

    public void addArcRandom(int x, int y) {
        if (!this.getHmap().containsKey(x)) {
            addNoeud(x);
        }
        if (!this.getHmap().containsKey(y)) {
            addNoeud(y);
        }
        addArc(x, y);
    }

    public int[] bijection(int i) {
        int tab[] = new int[2];
        int v = 1 + (int) Math.floor(-1 / 2 + Math.sqrt(1 / 4 + 2 * i));
        int w = i - v * (v - 1) / 2;
        tab[0] = v;
        tab[1] = w;
        return tab;
    }

    public boolean containsArc(int x, int y) {
        if (!this.getHmap().containsKey(x))
            return true;
        else if (!this.getHmap().containsKey(y))
            return true;
        else {
            if (!this.getHmap().get(x).hasSuccesseur(y))
                return false;
        }
        return true;
    }

    private int fact(int v) {
        return (v == 0) ? 1 : (v * (fact(v - 1)));
    }

    public int combi(int n, int k) {
        return (int) (fact(n) / (fact(k) * fact(n - k)));
    }
}