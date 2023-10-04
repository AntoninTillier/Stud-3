package tla.core;

public class State implements Comparable<State> {

    private String label;

    public State(String s) {
        label = s;
    }

    public int compareTo(State q) {
        return label.compareTo(q.label);
    }

    @Override
    public boolean equals(Object q) {
        if (this == q) {
            return true;
        }
        if (!(q instanceof State)) {
            return false;
        }
        State s = (State) q;
        return label.equals(s.label);
    }

    public boolean equals(String s) {
        return label.equals(s);
    }

    @Override
    public String toString() {
        return label;
    }
}
