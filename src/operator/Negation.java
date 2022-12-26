package operator;

public class Negation extends Operator {

    final int priority = 1;
    final int amountOfVariables = 1;
    final String mark = "!";

    @Override
    public boolean execute(boolean... variable) {
        return !variable[0];
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
