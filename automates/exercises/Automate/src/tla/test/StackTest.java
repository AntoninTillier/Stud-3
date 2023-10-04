package tla.test;

import tla.core.Symbol;
import tla.core.Word;
import tla.pda.Stack;

public class StackTest {

    public static void run() {
        Word w = new Word();
        Symbol Z0 = new Symbol("Z0");
        Symbol X = new Symbol("X");
        Symbol Y = new Symbol("Y");
        Symbol E = new Symbol("E");
        Symbol plus = new Symbol("+");
        //Symbol star = new Symbol("*");
        Stack S = new Stack();
        System.out.println(S);
        S.push(Z0);
        System.out.println("Push Z0: " + S);
        S.push(X);
        S.push(X);
        S.push(Y);
        System.out.println("Push X puis X puis Y: " + S);
        S.pop();
        System.out.println("Pop: " + S);
        S.pop();
        System.out.println("Pop: " + S);
        S.pop();
        System.out.println("Pop: " + S);
        S.pop();
        System.out.println("Pop: " + S);
        S.push(E);
        System.out.println("Push E: " + S);
        w.append(E);
        w.append(plus);
        w.append(E);
        S.replace(w);
        System.out.println("Replace E+E: " + S);
    }
}
