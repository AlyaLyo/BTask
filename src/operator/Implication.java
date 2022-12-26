package operator;

public class Implication extends Operator {

    final int priority = 4;
    final int amountOfVariables = 2;
    final String mark = "->";

    @Override
    public boolean execute(boolean... variable) {
        if (!variable[0]) {
            return true;
        }
        else {
            if (!variable[1]) {
                return false;
            }
            return true;
        }
    }

    @Override
    public int getPriority() {
        return priority;
    }

    @Override
    public int getAmountOfVariables() {
        return amountOfVariables;
    }

    @Override
    public String getMark() {
        return mark;
    }
}
