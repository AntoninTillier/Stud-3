package tla.fsa;

import tla.core.State;
import tla.core.StateSet;
import tla.core.Symbol;
import tla.core.Word;

public abstract class AutomatonDfa extends Automaton {

    public boolean accepts(Word w) {
        State p = Delta(w);
        if (p == null) {
            return false;
        }
        return F.contains(p);
    }

    private State Delta(Word w) {
        State q = init;
        for (Symbol a : w) {
            System.out.print("  " + q + " --(" + a + ")--> ");
            q = delta(q, a);
            System.out.println(q);
            if (q == null) {
                break;
            }
        }
        return q;
    }

    private State delta(State q, Symbol a) {
        StateSet P = T.get(q, a);
        if (P == null) {
            return null;
        }
        return P.first();
    }
}
