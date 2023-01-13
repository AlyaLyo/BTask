package operator;

public interface Operator {

    public boolean execute(boolean... variable);

    public int getPriority();

    public int getAmountOfVariables();

    public String getMark();

}
