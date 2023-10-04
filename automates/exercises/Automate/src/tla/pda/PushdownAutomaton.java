package tla.pda;


import tla.core.State;
import tla.core.Symbol;
import tla.core.Word;

public class PushdownAutomaton {

    public static Symbol epsilon = new Symbol("/epsilon/");
    protected Stack S = new Stack();
    protected StackTransitionTable T = new StackTransitionTable();
    protected State init = null;

    public boolean accepts(Word w) {
        boolean res = false;
        ActionList al = delta(init, epsilon, S.getFirst());
        S.pop();
        for (Action ac : al) {
            res = calStack(init, ac, w, S);
            if (res == true) {
                return true;
            }
        }
        return res;
    }

    private boolean calStack(State init2, Action ac, Word w, Stack S) {
        init = ac.state;
        Stack S1 = new Stack();
        Word w1 = new Word();
        w1.addAll(w);
        S1.addAll(S);
        Stack temp = new Stack();
        temp.addAll(S1);
        S1.clear();
        for (Symbol s : ac.word) {
            S1.add(s);
        }
        S1.addAll(temp);
        int i = w.size();
        boolean res = false;
        while (i >= 0 && !res) {

            if (S1.isEmpty() && w1.isEmpty()) {
                return true;
            } else if (S1.isEmpty() && !w1.isEmpty()) {
                return false;
            } else if ((!S1.isEmpty() && w1.isEmpty())) {
                return false;

            }
            if (!S1.getFirst().equals("P")) {
                i--;

                if (S1.get(0).equals(w1.get(0)) && (w1.get(0).equals("0") || w1.get(0).equals("1"))) {
                    S1.pop();
                    w1.remove(0);
                }
            } else {
                boolean res2 = false;
                ActionList al = delta(init, epsilon, S1.get(0));
                S1.pop();
                for (Action ac2 : al) {
                    Stack S2 = new Stack();
                    S2.addAll(S1);
                    res2 = calStack(init, ac2, w1, S2);
                    if (res2 == true) {
                        res = true;
                    }
                }
            }
        }
        return res;

    }

    public ActionList delta(State q, Symbol a, Symbol s) {
        return T.get(q, a, s);
    }
}
