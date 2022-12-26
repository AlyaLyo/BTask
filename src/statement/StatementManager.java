package statement;

import operator.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class StatementManager {

    public static Statement parseStatement (String line) {

        line = line.replaceAll("\\s", "");

        String variable = "";
        ArrayList<String> variables = new ArrayList<>();

        Map<Integer, Integer> operatorsPriorities = new HashMap<>();
        int position;
        int priority = 0;

        int bracketsCount = 0;

        for (int i = 0; i < line.length(); i++) {

            if (isOperator(String.valueOf(line.charAt(i)),priority) || isOperator(line.substring(i, i+1),priority)) {
                position = i;
                operatorsPriorities.put(position,priority);
                if (variable != "") {
                    if (!variables.contains(variable)) {
                        variables.add(variable);
                    }
                    variable = "";
                }
            }
            else {
                if (String.valueOf(line.charAt(i)).equals("(")) {
                    bracketsCount = bracketsCount + 1;
                }
                else {
                    if (!String.valueOf(line.charAt(i)).equals(">") || !String.valueOf(line.charAt(i)).equals(")")) {

                        variable = variable + String.valueOf(line.charAt(i));

                    }
                }
            }

        }

        return new Statement(variables, operatorsPriorities, line, bracketsCount);

    }

    private static boolean isOperator(String mark, int priority) {
        if (mark.equals("!")) {
            priority = 1;
            return true;
        }
        if (mark.equals("&")) {
            priority = 2;
            return true;
        }
        if (mark.equals("|")) {
            priority = 3;
            return true;
        }
        if (mark.equals("->")) {
            priority = 4;
            return true;
        }
        return false;
    }


}
