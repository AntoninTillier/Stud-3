package tla.core;

public class Symbol implements Comparable<Symbol> {

    private String value;

    public Symbol(char c) {
        value = String.valueOf(c);
    }

    public Symbol(String s) {
        value = s;
    }

    public int compareTo(Symbol a) {
        return value.compareTo(a.value);
    }

    @Override
    public boolean equals(Object a) {
        if (this == a) {
            return true;
        }
        if (!(a instanceof Symbol)) {
            return false;
        }
        Symbol s = (Symbol) a;
        return value.equals(s.value);
    }

    public boolean equals(String s) {
        return value.equals(s);
    }

    @Override
    public String toString() {
        return value;
    }
}
