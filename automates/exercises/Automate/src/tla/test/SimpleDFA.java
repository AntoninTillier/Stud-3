package tla.test;

import tla.core.State;
import tla.core.Symbol;
import tla.core.Word;
import tla.fsa.Automaton;
import tla.fsa.AutomatonDfa;

import java.util.Scanner;

public class SimpleDFA extends AutomatonDfa {

    private static Scanner scanner;

    public SimpleDFA() {
        State q0 = new State("q0");
        State q1 = new State("q1");
        State q2 = new State("q2");
        State q3 = new State("q3");
        Symbol a = new Symbol("a");
        Symbol b = new Symbol("b");
        Symbol c = new Symbol("c");
        Symbol d = new Symbol("d");
        setInitialState(q0);
        F.add(q3);
        T.put(q0, a, q1);
        T.put(q1, a, q2);
        T.put(q1, b, q1);
        T.put(q1, c, q3);
        T.put(q2, b, q0);
        T.put(q2, d, q3);
    }

    public static void run() {
        Word word;
        System.out.print("Please input a string to test the DFA: ");
        scanner = new Scanner(System.in);
        word = new Word(scanner.next());
        Automaton simpleDfa = new SimpleDFA();
        if (simpleDfa.accepts(word)) {
            System.out.println(word + " accepted.");
        } else {
            System.out.println(word + " cannot be accepted.");
        }
    }
}
