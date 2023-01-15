package operator;

public interface Operator {

    public boolean execute(boolean... variable);

    public int getPriority();
    public void setPriority(int priority);

    public int getAmountOfVariables();

    public String getMark();

}
