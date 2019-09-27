package bean;

/**
 * @author liuyoubin
 * @date 2019/9/27 - 21:44
 */
public class Expression {
    private String[] operators;
    private Number[] numbers;

    public String[] getOperators() {
        return operators;
    }

    public void setOperators(String[] operators) {
        this.operators = operators;
    }

    public Number[] getNumbers() {
        return numbers;
    }

    public void setNumbers(Number[] numbers) {
        this.numbers = numbers;
    }

    public Expression(String[] operators, Number[] numbers) {
        this.operators = operators;
        this.numbers = numbers;
    }

    public Expression() {
    }
}
