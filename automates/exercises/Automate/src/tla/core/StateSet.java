package tla.core;

import java.util.TreeSet;

@SuppressWarnings("serial")
public class StateSet extends TreeSet<State> {

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("{");
        for (State q : this) {
            if (s.length() == 1) {
                s.append(q.toString());
            } else {
                s.append(",").append(q.toString());
            }
        }
        s.append("}");
        return s.toString();
    }
}
