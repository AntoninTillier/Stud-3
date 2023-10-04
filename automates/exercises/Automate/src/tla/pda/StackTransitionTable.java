package tla.pda;

import tla.core.State;
import tla.core.Symbol;
import tla.core.Word;

import java.util.TreeMap;

@SuppressWarnings("serial")
public class StackTransitionTable extends TreeMap<State, TreeMap<Symbol, TreeMap<Symbol, ActionList>>> {

    public ActionList get(State q, Symbol a, Symbol s) {
        TreeMap<Symbol, TreeMap<Symbol, ActionList>> T1 = this.get(q);
        if (T1 != null) {
            TreeMap<Symbol, ActionList> T2 = T1.get(a);
            if (T2 != null) {
                return T2.get(s);
            }
        }
        return null;
    }

    public void put(State q, Symbol a, Symbol s, State p) {
        TreeMap<Symbol, TreeMap<Symbol, ActionList>> T1 = this.computeIfAbsent(q, k -> new TreeMap<>());
        TreeMap<Symbol, ActionList> T2 = T1.computeIfAbsent(a, k -> new TreeMap<>());
        ActionList L = T2.computeIfAbsent(s, k -> new ActionList());
        Action x = new Action(p, new Word());
        for (Action xx : L) {
            if (xx.equals(x)) {
                return;
            }
        }
        L.add(x);
    }

    public void put(State q, Symbol a, Symbol s, Symbol z, State p) {
        TreeMap<Symbol, TreeMap<Symbol, ActionList>> T1 = this.computeIfAbsent(q, k -> new TreeMap<>());
        TreeMap<Symbol, ActionList> T2 = T1.computeIfAbsent(a, k -> new TreeMap<>());
        ActionList L = T2.computeIfAbsent(s, k -> new ActionList());
        Action x = new Action(p, new Word(z));
        for (Action xx : L) {
            if (xx.equals(x)) {
                return;
            }
        }
        L.add(x);
    }

    public void put(State q, Symbol a, Symbol s, Word z, State p) {
        TreeMap<Symbol, TreeMap<Symbol, ActionList>> T1 = this.computeIfAbsent(q, k -> new TreeMap<>());
        TreeMap<Symbol, ActionList> T2 = T1.computeIfAbsent(a, k -> new TreeMap<>());
        ActionList L = T2.computeIfAbsent(s, k -> new ActionList());
        Action x = new Action(p, z);
        for (Action xx : L) {
            if (xx.equals(x)) {
                return;
            }
        }
        L.add(x);
    }

    @Override
    public String toString() {
        StringBuilder ts = new StringBuilder();
        for (State q : this.keySet()) {
            TreeMap<Symbol, TreeMap<Symbol, ActionList>> T1 = this.get(q);
            if (T1 == null) {
                continue;
            }
            for (Symbol a : T1.keySet()) {
                TreeMap<Symbol, ActionList> T2 = T1.get(a);
                if (T2 == null) {
                    continue;
                }
                for (Symbol s : T2.keySet()) {
                    ActionList L = T2.get(s);
                    if (L == null) {
                        continue;
                    }
                    for (Action x : L) {
                        ts.append(q).append(" --(").append(a).append(",").append(s).append("/");
                        if (x.word.size() > 0) {
                            ts.append(x.word.get(0));
                            for (int i = 1; i < x.word.size(); ++i) {
                                ts.append(".").append(x.word.get(i));
                            }
                        }
                        ts.append(")--> ").append(x.state).append("\n");
                    }
                }
            }
        }
        return ts.toString();
    }
}
