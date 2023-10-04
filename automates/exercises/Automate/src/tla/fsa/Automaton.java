package tla.fsa;

import tla.core.State;
import tla.core.StateSet;
import tla.core.TransitionTable;
import tla.core.Word;

public abstract class Automaton {

    protected StateSet F = new StateSet();
    protected TransitionTable T = new TransitionTable();
    protected StateSet empty = new StateSet();
    protected State init = null;

    abstract public boolean accepts(Word w);

    protected void setInitialState(State q) {
        init = q;
    }
}
