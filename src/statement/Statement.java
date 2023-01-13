package statement;

import operator.Operator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Statement {

    private static ArrayList<String> variables;
    private static Map<Integer, Integer> operatorsPriorities;
    private static String content;
    private static int bracketsAmount;

    public Statement(ArrayList<String> variables, TreeMap<Integer, Integer> operatorsPriorities, String content, int bracketsAmount) {
        this.variables = variables;
        this.operatorsPriorities = operatorsPriorities;
        this.content = content;
        this.bracketsAmount = bracketsAmount;
    }

    private static int execute(Map<String, Integer> variables, TreeMap<Integer, Integer> operatorsPriorities, String content) {
        while (!operatorsPriorities.isEmpty()) {

        }
    }

    public static int[] getTruthTable() {

        int trueCount = 0;
        int falseCount = 0;

        Map<String, Integer> table = new HashMap<>();
        for (String variable : variables) {
            table.put(variable, 0);
        }

        for (int i = 0; i < variables.size()*variables.size(); i++) {

            int count = operatorsPriorities.size();

            while (count > 0) {

                int temporaryBracketsAmount = bracketsAmount;
                while (temporaryBracketsAmount > 0) {

                    int closeBracketPosition = 0;
                    while (content.charAt(closeBracketPosition) != ')') {
                        closeBracketPosition = closeBracketPosition + 1;
                    }
                    int openBracketPosition = closeBracketPosition;
                    while (content.charAt(openBracketPosition) != '(') {
                        openBracketPosition = openBracketPosition - 1;
                    }

                    temporaryBracketsAmount = temporaryBracketsAmount - 1;


                }

            }

        }

        return ;

    }

}
