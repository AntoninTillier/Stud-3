package tla;

import tla.test.*;

public class Main {

    public static void main(String[] args) {
        // (1) Simple DFA
        //SimpleDFA.run();

        // (2) Simple NFA
        //SimpleNFA.run();

        // (3) Simple NFA-Epsilon
        //SimpleNFAe.run();

        // (4) Test the Stack
        //StackTest.run();

        // (5) Test the StackTransitionTable
        //StackTransitionTableTest.run();

        // (6) Test the Stack
        SimplePDA.run();
    }
}
