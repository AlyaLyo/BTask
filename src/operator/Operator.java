package operator;

public abstract class Operator {

    private int priority;
    private int amountOfVariables;
    private String mark;

    public abstract boolean execute(boolean... variable);

    public abstract int getPriority();

    public abstract int getAmountOfVariables();

    public abstract String getMark();

}
