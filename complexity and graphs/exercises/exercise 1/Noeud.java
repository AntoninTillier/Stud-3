package TP1;

import java.util.LinkedList;

public class Noeud {
    private int id;
    private LinkedList<Arc> succ = new LinkedList<Arc>();
    private boolean mark = false;

    public Noeud(int id) {
        this.id = id;
    }

    public boolean hasSuccesseur(int j) {
        for (Arc arc : succ) {
            if (arc.getSource().getId() == this.id && arc.getCible().getId() == j) {
                return true;
            }
        }
        return false;
    }

    public double distArc(Noeud n) {
        if (hasSuccesseur(n.id)) {
            for (int i = 0; i < succ.size(); i++) {
                if (succ.get(i).getSource().getId() == id && succ.get(i).getCible().getId() == n.id)
                    return succ.get(i).getPoids();
            }
        }
        return 0;
    }

    public String toString() {
        return String.valueOf(this.id);
    }

    public int getId() {
        return this.id;
    }

    public LinkedList<Arc> getSucc() {
        return this.succ;
    }

    public boolean getMark() {
        return this.mark;
    }

    public void setMark(boolean v) {
        this.mark = v;
    }
}