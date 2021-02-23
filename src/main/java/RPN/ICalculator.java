package RPN;

public interface ICalculator {

    /**This method calculates a mathematical expression.
     * The method evaluates the expression and returns the corresponding result.
     * If the expression cannot be evaluated, an appropriate error is returned.
     * And also the division error is not zero.
     * @param input infix mathematical expression
     * @return calculation result
     */
    double calculate(String input) throws IllegalArgumentException, ArithmeticException;
}
