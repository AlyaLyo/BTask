package statement;

import operator.Operator;

public class StatementNode {
    public String name;
    public StatementNode left;
    public StatementNode right;
    public StatementNode parent;

    public boolean value;

    public NodeType nodeType;

    public Operator operator;

    public boolean exec() {
        if (operator == null) return value;

        if (right != null) {
            value = operator.execute(left.exec(), right.exec());
        } else {
            value = operator.execute(left.exec());
        }

        return value;
    }
}
