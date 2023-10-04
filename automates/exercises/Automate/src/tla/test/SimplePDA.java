package tla.test;

import tla.core.State;
import tla.core.Symbol;
import tla.core.Word;
import tla.pda.PushdownAutomaton;

import java.util.Scanner;

public class SimplePDA extends PushdownAutomaton {

    private static Scanner scanner;

    // We define a PDA for the following grammar:
    // P -> 0P1 | 01
    public SimplePDA() {
        Symbol P = new Symbol("P");
        Symbol a0 = new Symbol("0");
        Symbol a1 = new Symbol("1");

        State q = new State("q");

        Word zo = new Word(a0).append(a1);
        Word zPo = new Word(a0).append(P).append(a1);

        init = q;
        S.push(P);
        T.put(q, epsilon, P, zPo, q);
        T.put(q, epsilon, P, zo, q);
        T.put(q, a0, a0, q);
        T.put(q, a1, a1, q);
        System.out.println(T);

    }

    public static void run() {
        Word word;
        System.out.print("Please input a string to test the PDA: ");
        scanner = new Scanner(System.in);
        word = new Word(scanner.next());
        PushdownAutomaton SimplePDA = new SimplePDA();
        if (SimplePDA.accepts(word)) {
            System.out.println(word + " accepted.");
        } else {
            System.out.println(word + " cannot be accepted.");
        }
    }
}
