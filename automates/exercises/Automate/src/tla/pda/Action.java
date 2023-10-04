package tla.pda;

import tla.core.State;
import tla.core.Word;

public class Action {

    public State state = null;
    public Word word = null;

    public Action(State state, Word word) {
        this.state = state;
        this.word = word;
    }

    @Override
    public boolean equals(Object q) {
        if (this == q) {
            return true;
        }
        if (!(q instanceof Action)) {
            return false;
        }
        Action x = (Action) q;
        return state.equals(x.state) && word.equals(x.word);
    }
}
