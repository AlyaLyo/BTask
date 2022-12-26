package statement;

import operator.Operator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Statement {

    private static ArrayList<String> variables;
    private static Map<Integer, Integer> operatorsPriorities;
    private static String content;
    private static int bracketsAmount;

    public Statement(ArrayList<String> variables, Map<Integer, Integer> operatorsPriorities, String content, int bracketsAmount) {
        this.variables = variables;
        this.operatorsPriorities = operatorsPriorities;
        this.content = content;
        this.bracketsAmount = bracketsAmount;
    }

    public static int[] getTruthTable() {

        int t = 0;
        int f = 0;

        Map<String, Integer> table = new HashMap<>();
        for (String variable : variables) {
            table.put(variable, 0);
        }

        for (int i = 0; i < variables.size()*variables.size(); i++) {

            while (!operatorsPriorities.isEmpty()) {

            }

        }

        return ;

    }

}
