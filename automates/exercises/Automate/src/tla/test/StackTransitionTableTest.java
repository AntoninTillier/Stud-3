package tla.test;

import tla.core.State;
import tla.core.Symbol;
import tla.core.Word;
import tla.pda.PushdownAutomaton;
import tla.pda.StackTransitionTable;

public class StackTransitionTableTest {

    public static void run() {
        State q = new State("q");
        State p = new State("p");
        Symbol a = new Symbol("a");
        Symbol b = new Symbol("b");
        Symbol Z = new Symbol("Z");
        Symbol X = new Symbol("X");
        Word XZ = new Word().append(X).append(Z);
        Word XX = new Word().append(X).append(X);
        StackTransitionTable ST = new StackTransitionTable();
        ST.put(q, a, Z, XZ, q); // a, Z/XZ
        ST.put(q, a, X, XX, q); // a, X/XX
        ST.put(q, PushdownAutomaton.epsilon, X, X, p); // epsilon, X/X
        ST.put(p, b, X, p);  // b, X/epsilon
        System.out.println(ST);
    }
}
