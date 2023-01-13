import eval.Eval;
import parser.Parser;
import statement.StatementNode;

public class Main {
    private static void printTree(StatementNode statementNode) {
        if (statementNode == null) {
            return;
        }
        System.out.println(statementNode.name);
        printTree(statementNode.left);
        printTree(statementNode.right);
    }
    public static void main(String[] args) {
        Parser parser = new Parser();

        parser.parse("A|!A");
        System.out.println(parser.getTokens().toString());

        StatementNode statementNode = parser.buildTree(0, parser.getTokens().size(), null);
        printTree(statementNode);

        Eval eval = new Eval(statementNode, parser.getVars());
        eval.eval();
    }
}