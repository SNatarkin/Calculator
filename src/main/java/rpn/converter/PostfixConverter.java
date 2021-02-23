package rpn.converter;

import rpn.operators.OperationType;

import java.util.Stack;

public class PostfixConverter implements Converter {
    @Override
    public String convertToPostfix(String input) {
        StringBuilder copyInput = new StringBuilder();
        Stack<String> operationStack = new Stack<>();
        for (int i = 0; i < input.length(); i++) {
            if (!OperationType.IsOperator(String.valueOf(input.charAt(i)))) {
                copyInput.append(input.charAt(i));
            } else {
                copyInput.append(" ");
                if (operationStack.size() > 0) {
                    while (!operationStack.isEmpty() && OperationType.getPriority(String.valueOf(input.charAt(i)))
                            <= OperationType.getPriority(operationStack.peek())) {
                        copyInput.append(operationStack.pop()).append(" ");
                    }
                }
                operationStack.push(String.valueOf(input.charAt(i)));
            }
        }
        while (!operationStack.isEmpty())
            copyInput.append(" ").append(operationStack.pop());
        return copyInput.toString();
    }
}





