package tla.pda;

import tla.core.Symbol;
import tla.core.Word;

import java.util.LinkedList;

@SuppressWarnings("serial")
public class Stack extends LinkedList<Symbol> {

    // The method pop() has been defined in the class LinkedList.
    // The method push() has been defined in the class LinkedList.

    public void replace(Word w) {
        this.pop();
        for (int i = w.size(); i > 0; --i) {
            this.push(w.get(i - 1));
        }
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("|");
        if (this.isEmpty()) {
            return s.append("|").toString();
        }
        for (Symbol z : this) {
            s.append(z).append("|");
        }
        return s.toString();
    }
}
