package operator;

public class Conjuction extends Operator {

    final int priority = 2;
    final int amountOfVariables = 2;
    final String mark = "&";

    @Override
    public boolean execute(boolean... variable) {
        if (variable[0] && variable[1]) {
            return true;
        }
        else {
            return false;
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
