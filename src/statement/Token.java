package statement;

import operator.Operator;

public class Token {
    public String name;
    public boolean isOperator;
    public final Operator operator;

    public Token(String name, boolean isOperator, Operator operator) {
        this.name = name;
        this.isOperator = isOperator;
        this.operator = operator;
    }

    @Override
    public String toString() {
        return name + ' ' + isOperator;
    }
}
