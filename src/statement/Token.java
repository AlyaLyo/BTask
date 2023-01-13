package statement;

import operator.Operator;

public class Token {
    public String name;
    public boolean isOperator;

    public int priority = -1;
    public final Operator operator;

    public Token(String name, boolean isOperator, int priority, Operator operator) {
        this.name = name;
        this.isOperator = isOperator;
        this.priority = priority;
        this.operator = operator;
    }

    @Override
    public String toString() {
        return name + ' ' + isOperator + ' ' + priority;
    }
}
