package tla.fsa;

import tla.core.State;
import tla.core.StateSet;
import tla.core.Symbol;
import tla.core.Word;

public abstract class AutomatonNfa extends Automaton {

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
        Q.add(init);
        for (Symbol a : w) {
            P.clear();
            System.out.print("  " + Q + " --(" + a + ")--> ");
            for (State q : Q) {
                StateSet R = delta(q, a);
                if (R != null) {
                    P.addAll(R);
                }
            }
            if (P.size() == 0) {
                return null;
            }
            System.out.println(P);
            Q.clear();
            Q.addAll(P);
        }
        return P;
    }

    private StateSet delta(State q, Symbol a) {
        return T.get(q, a);
    }
}
