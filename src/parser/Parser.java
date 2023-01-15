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
        int bracketsCount = 0;
        StringBuilder currentVarName = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            if (ch == ' ' || ch == '\t' || ch == '\r' || ch == '>') continue;
            if (!(ch >= 'A' && ch <= 'Z' || ch >= '0' && ch <= '9' || ch == 39 || ch == '!' || ch == '(' || ch == ')' || ch == '|' || ch == '&' || ch == '-'))
                continue;

            if (ch == '(') {
                bracketsCount++;
                if (!currentVarName.toString().isEmpty()) {
                    Token token = new Token(currentVarName.toString(), false, null);
                    tokens.add(token);
                    currentVarName = new StringBuilder();
                }
                continue;
            }
            if (ch == ')') {
                bracketsCount--;
                if (!currentVarName.toString().isEmpty()) {
                    Token token = new Token(currentVarName.toString(), false, null);
                    tokens.add(token);
                    currentVarName = new StringBuilder();
                }
                continue;
            }

            if (ch == '!') {
                if (!currentVarName.toString().isEmpty()) {
                    Token token = new Token(currentVarName.toString(), false, null);
                    tokens.add(token);
                    currentVarName = new StringBuilder();
                }
                Token token = new Token("!", true, new Negation());
                token.operator.setPriority(token.operator.getPriority() + 10 * bracketsCount);
                tokens.add(token);
                continue;
            }

            if (ch == '&') {
                if (!currentVarName.toString().isEmpty()) {
                    Token token = new Token(currentVarName.toString(), false, null);
                    tokens.add(token);
                    currentVarName = new StringBuilder();
                }
                Token token = new Token("&", true, new Conjuction());
                token.operator.setPriority(token.operator.getPriority() + 10 * bracketsCount);
                tokens.add(token);
                continue;
            }
            if (ch == '|') {
                if (!currentVarName.toString().isEmpty()) {
                    Token token = new Token(currentVarName.toString(), false, null);
                    tokens.add(token);
                    currentVarName = new StringBuilder();
                }
                Token token = new Token("|", true, new Disjunction());
                token.operator.setPriority(token.operator.getPriority() + 10 * bracketsCount);
                tokens.add(token);
                continue;
            }
            if (ch == '-') {
                if (i + 1 >= input.length()) continue;
                if (input.charAt(i + 1) != '>') continue;
                if (!currentVarName.toString().isEmpty()) {
                    Token token = new Token(currentVarName.toString(), false, null);
                    tokens.add(token);
                    currentVarName = new StringBuilder();
                }
                Token token = new Token("->", true, new Implication());
                token.operator.setPriority(token.operator.getPriority() + 10 * bracketsCount);
                tokens.add(token);
                continue;
            }

            if (currentVarName.toString().isEmpty()) {
                if (ch >= '0' && ch <= '9' || ch == 39) {
                    continue;
                }
            }

            currentVarName.append(ch);
        }
        if (!currentVarName.toString().isEmpty()) {
            Token token = new Token(currentVarName.toString(), false, null);
            tokens.add(token);
        }
    }

    public void uncringe() {
        int indToRemove = -1;
        for (int i = 0; i < tokens.size() - 1; i++) {
            Token tk = tokens.get(i);
            Token nextTk = tokens.get(i + 1);

            if (tk.isOperator && nextTk.isOperator && !nextTk.name.equals("!")) {
                if (tk.name.equals("!")) {
                    indToRemove = i;
                    break;
                }
                indToRemove = i + 1;
//                if (tk.operator.getPriority() > nextTk.operator.getPriority()) {
//                    if (tk.name.equals("!")){
//                        indToRemove = i;
//                        break;
//                    }
//                    indToRemove = i + 1;
//                }
//                else {
//                    indToRemove = i;
//                }
//                break;
            }
        }
        if (indToRemove != -1) {
            tokens.remove(indToRemove);
            uncringe();
        }
    }


    public StatementNode buildTree(int leftInd, int rightInd, StatementNode parent) {

        int ind = leftInd;
        int priority = Integer.MAX_VALUE;

        for (int i = leftInd; i < rightInd; i++) {
            Token tk = tokens.get(i);
            if (!tk.isOperator) continue;
            if (tk.operator.getPriority() < priority) {
                ind = i;
                priority = tk.operator.getPriority();
            }
        }

        Token tk = tokens.get(ind);
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
