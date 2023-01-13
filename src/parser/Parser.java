package parser;

import operator.Conjuction;
import operator.Disjunction;
import operator.Implication;
import operator.Negation;
import statement.NodeType;
import statement.StatementNode;
import statement.Token;

import java.util.ArrayList;

public class Parser {

    private ArrayList<Token> tokens = new ArrayList<>();
    private ArrayList<StatementNode> vars = new ArrayList<>();


    public void parse(String input) {
        StringBuilder currentVarName = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            if (ch == ' ' || ch == '>') continue;

            if (ch == '(') {
                if (!currentVarName.isEmpty()) {
                    Token token = new Token(currentVarName.toString(), false, -1, null);
                    tokens.add(token);
//                    vars.add(currentVarName.toString());
                    currentVarName = new StringBuilder();
                }
                Token token = new Token("(", false, -1, null);
                tokens.add(token);
                continue;
            }
            if (ch == ')') {
                if (!currentVarName.isEmpty()) {
                    Token token = new Token(currentVarName.toString(), false, -1, null);
                    tokens.add(token);
//                    vars.add(currentVarName.toString());
                    currentVarName = new StringBuilder();
                }
                Token token = new Token(")", false, -1, null);
                tokens.add(token);
                continue;
            }

            if (ch == '!') {
                if (!currentVarName.isEmpty()) {
                    Token token = new Token(currentVarName.toString(), false, -1, null);
                    tokens.add(token);
//                    vars.add(currentVarName.toString());
                    currentVarName = new StringBuilder();
                }
                Token token = new Token("!", true, 4, new Negation());
                tokens.add(token);
                continue;
            }

            if (ch == '&') {
                if (!currentVarName.isEmpty()) {
                    Token token = new Token(currentVarName.toString(), false, -1, null);
                    tokens.add(token);
//                    vars.add(currentVarName.toString());
                    currentVarName = new StringBuilder();
                }
                Token token = new Token("&", true, 3, new Conjuction());
                tokens.add(token);
                continue;
            }
            if (ch == '|') {
                if (!currentVarName.isEmpty()) {
                    Token token = new Token(currentVarName.toString(), false, -1, null);
                    tokens.add(token);
//                    vars.add(currentVarName.toString());
                    currentVarName = new StringBuilder();
                }
                Token token = new Token("|", true, 2, new Disjunction());
                tokens.add(token);
                continue;
            }
            if (ch == '-') {
                if (!currentVarName.isEmpty()) {
                    Token token = new Token(currentVarName.toString(), false, -1, null);
                    tokens.add(token);
//                    vars.add(currentVarName.toString());
                    currentVarName = new StringBuilder();
                }
                Token token = new Token("->", true, 1, new Implication());
                tokens.add(token);
                continue;
            }

            currentVarName.append(ch);
        }
        if (!currentVarName.isEmpty()) {
            Token token = new Token(currentVarName.toString(), false, -1, null);
            tokens.add(token);
        }
    }

    public StatementNode buildTree(int leftInd, int rightInd, StatementNode parent) {
        int ind = leftInd;
        int priority = Integer.MAX_VALUE;
        int bracketsCount = 0;

        for (int i = leftInd; i < rightInd; i++) {
            Token tk = tokens.get(i);

            if (tk.name.equals("(")) {
                bracketsCount++;
            }
            if (tk.name.equals(")")) {
                bracketsCount--;
            }
            if (!tk.isOperator) continue;
            if (tk.priority + (10 * bracketsCount) <= priority) {
                ind = i;
                priority = tk.priority + (10 * bracketsCount);
            }
        }

        Token tk = tokens.get(ind);
        int k = 0;
        while (tk.name.equals("(")) {
            tk = tokens.get(ind + ++k);
        }
        StatementNode statementNode = new StatementNode();
        statementNode.parent = parent;
        statementNode.name = tk.name;
        if (!tk.isOperator) {
            statementNode.nodeType = NodeType.VAR;
            vars.add(statementNode);
            return statementNode;
        }

        statementNode.nodeType = NodeType.OP;
        statementNode.operator = tk.operator;

        if (statementNode.name.equals("!")) {
            statementNode.left = buildTree(ind + 1, rightInd, statementNode);
            return statementNode;
        }


        statementNode.left = buildTree(leftInd, ind, statementNode);
        statementNode.right = buildTree(ind + 1, rightInd, statementNode);
        return statementNode;
    }

    public ArrayList<StatementNode> getVars() {
        return vars;
    }

    public ArrayList<Token> getTokens() {
        return tokens;
    }
}
