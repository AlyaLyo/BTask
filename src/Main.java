import eval.Eval;
import parser.Parser;
import statement.StatementNode;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.Scanner;

public class Main {
    private static void printTree(StatementNode statementNode) {
        if (statementNode == null) {
            return;
        }
//        System.out.println(statementNode.name);
        printTree(statementNode.left);
        printTree(statementNode.right);
    }

    public static void main(String[] args) throws IOException {
        Parser parser = new Parser();

        Scanner sc = new Scanner(System.in);

        InputStreamReader inputStreamReader = new InputStreamReader(System.in);

        StringBuilder sb = new StringBuilder();
        while (true){
            int t = inputStreamReader.read();
            if (t == '\n') break;
            sb.append((char)t);
        }


//        System.out.println(sb.toString());
        parser.parse(sb.toString());
//        System.out.println(parser.getTokens().toString());
        parser.uncringe();
        StatementNode statementNode = parser.buildTree(0, parser.getTokens().size(), null);
//        printTree(statementNode);

        Eval eval = new Eval(statementNode, parser.getVars());
        eval.eval();
    }
}