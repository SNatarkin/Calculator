package rpn.converter;

public interface Converter {

    /**
     * This method converts infix expression to postfix expression.
     * Returns an expression in which the operands are located before the operation signs.
     * For example, consider evaluating the expression 7 2 3 * - (equivalent expression in infix notation: 7-2*3)ю
     * @param input infix expression
     * @return postfix expression
     */
    String convertToPostfix(String input);


}
