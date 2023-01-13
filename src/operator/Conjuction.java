package operator;

public class Conjuction implements Operator {

    final int priority = 3;
    final int amountOfVariables = 2;
    final String mark = "&";

    @Override
    public boolean execute(boolean... variable) {
        return variable[0] && variable[1];
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
