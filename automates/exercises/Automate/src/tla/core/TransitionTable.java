package tla.core;

import java.util.TreeMap;

@SuppressWarnings("serial")
public class TransitionTable extends TreeMap<State, TreeMap<Symbol, StateSet>> {

    public StateSet get(State q, Symbol a) {
        TreeMap<Symbol, StateSet> t = this.get(q);
        if (t == null) {
            return null;
        }
        return t.get(a);
    }

    public void put(State q, Symbol a, State p) {
        TreeMap<Symbol, StateSet> t = this.get(q);
        if (t == null) {
            t = new TreeMap<>();
        }
        StateSet P = t.get(a);
        if (P == null) {
            P = new StateSet();
        }
        P.add(p);
        t.put(a, P);
        this.put(q, t);
    }

    public TransitionTable put(State q, Symbol a, StateSet P) {
        TreeMap<Symbol, StateSet> t = this.get(q);
        if (t == null) {
            t = new TreeMap<>();
        }
        t.put(a, P);
        this.put(q, t);
        return this;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (State q : this.keySet()) {
            TreeMap<Symbol, StateSet> m = this.get(q);
            if (m == null) {
                continue;
            }
            for (Symbol a : m.keySet()) {
                StateSet P = m.get(a);
                if (P == null) {
                    continue;
                }
                for (State p : P) {
                    s.append(q).append(" --(").append(a).append(")--> ").append(p).append("\n");
                }
            }
        }
        return s.toString();
    }
}
