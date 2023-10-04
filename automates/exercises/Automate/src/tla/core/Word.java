package tla.core;

import java.util.ArrayList;

@SuppressWarnings("serial")
public class Word extends ArrayList<Symbol> {

    public Word() { }

    public Word(Symbol s) {
        this.add(s);
    }

    public Word(String s) {
        for (int i = 0; i < s.length(); ++i) {
            this.add(new Symbol(s.charAt(i)));
        }
    }

    public Word append(String s) {
        this.add(new Symbol(s));
        return this;
    }

    public Word append(Symbol s) {
        this.add(s);
        return this;
    }

    @Override
    public boolean equals(Object w) {
        if (this == w) {
            return true;
        }
        if (!(w instanceof Word)) {
            return false;
        }
        Word x = (Word) w;
        if (x.size() != this.size()) {
            return false;
        }
        for (int i = 0; i < x.size(); ++i) {
            if (!x.get(i).equals(this.get(i))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder w = new StringBuilder();
        for (Symbol a : this) {
            w.append(a.toString());
        }
        return w.toString();
    }
}

