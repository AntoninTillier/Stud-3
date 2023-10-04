**Finite Deterministic Automata (FDAs)**

The functions Delta(w), delta(q, a), and accepts(w) discussed in the course for defining deterministic finite automata (DFAs) have been implemented. The following classes have been defined in the Java package 'tla.core':

- Automaton: The most general abstract class for defining automata.
- AutomatonDfa: The most general abstract class for defining deterministic finite automata (DFAs).
- State: Represents a state.
- StateSet: Represents a set of states, extending the 'Set<State>' class.
- Symbol: Represents a symbol.
- Transition: Represents a transition, extending the 'HashMap<Symbol, State>' class.
- TransitionTable: Represents the transition table, extending the 'HashMap<State, Transition>' class.
- Word: Represents a word, extending the 'ArrayList<Symbol>' class.

For this initial version, the transition table must be defined directly in the code. Implement the constructor (with TODO) defined in the 'SimpleDfa' class, then test this first automaton (refer to the 'Main' class).