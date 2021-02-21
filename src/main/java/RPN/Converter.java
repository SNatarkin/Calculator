package rpn;

public interface Converter {

    /**
     * @param input infix expression
     * @return postfix expression
     */
    String convertToPostfix(String input);


}
