**Pushdown Automata (2)**

**Exercise 1:**

Implement the following methods in the `StackTransitionTable` class:

- `get(State q, Symbol a, Symbol s)`: Return a target state based on state q, input symbol a, and the symbol at the top of the stack s.
- `put(State q, Symbol a, Symbol z, State p)`: Add a transition consisting of state q, input symbol a, symbol at the top of the stack s, and target state p for the pop case.
- `put(State q, Symbol a, Symbol z, Symbol x, State p)`: Add a transition consisting of state q, input symbol a, symbol at the top of the stack s, and target state p for the replace symbol z with symbol x case.
- `put(State q, Symbol a, Symbol z, Word x, State p)`: Add a transition consisting of state q, input symbol a, symbol at the top of the stack s, and target state p for the replace symbol z with word (multiple symbols) x case.
- `toString()`: Represent the transition table as a String.

**Exercise 2:**

Test the `StackTransitionTable` class introduced in the `Main` class.