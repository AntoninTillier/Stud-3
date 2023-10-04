package tla.fsa;

import tla.core.State;
import tla.core.StateSet;
import tla.core.Symbol;
import tla.core.Word;

public abstract class AutomatonNfaEpsilon extends Automaton {

    protected Symbol epsilon = new Symbol("/epsilon/");

    public boolean accepts(Word w) {
        StateSet P = Delta(w);
        if (P == null) {
            return false;
        }
        for (State p : P) {
            if (F.contains(p)) {
                return true;
            }
        }
        return false;
    }

    private StateSet Delta(Word w) {
        StateSet P = new StateSet();
        StateSet Q = new StateSet();
        Q.addAll(closure(init));
        for (Symbol a : w) {
            P.clear();
            System.out.print("  " + Q + " --(" + a + ")--> ");
            for (State q : Q) {
                StateSet R = delta(q, a);
                if (R != null) {
                    for (State r : R) {
                        P.addAll(closure(r));
                    }
                }
            }
            if (P.size() == 0) {
                System.out.println(empty);
                return null;
            }
            System.out.println(P);
            Q.clear();
            Q.addAll(P);
        }
        return P;
    }

    private StateSet closure(State q) {
        StateSet E = new StateSet();
        E.add(q);
        StateSet P = T.get(q, epsilon);
        if (P != null) {
            for (State p : P) {
                closure(p, E);
            }
        }
        return E;
    }

    private void closure(State q, StateSet E) {
        if (E.contains(q)) {
            return;
        }
        E.add(q);
        StateSet P = T.get(q, epsilon);
        if (P != null) {
            for (State p : P) {
                closure(p, E);
            }
        }
    }

    private StateSet delta(State q, Symbol a) {
        return T.get(q, a);
    }
}
