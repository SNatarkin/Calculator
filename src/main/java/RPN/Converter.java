package RPN;

import java.util.Stack;

public class Converter implements PostfixConverter {
    @Override
    public String convertToPostfix(String input) {
        StringBuffer copyInput = new StringBuffer();
        Stack<String> operStack = new Stack<>();
        for (int i = 0; i < input.length(); i++) {
            if (!OperationType.IsOperator(String.valueOf(input.charAt(i)))) {
                copyInput.append(input.charAt(i));
                if (i >= input.length()) break;
            } else {
                copyInput.append(" ");
                if (operStack.size() > 0) {
                    while ( !operStack.isEmpty() && OperationType.getPriority(String.valueOf(input.charAt(i))) <= OperationType.getPriority(operStack.peek())) {
                        copyInput.append(operStack.pop()+ " ");
                    }
                }
                operStack.push(String.valueOf(input.charAt(i)));

            }
        }
        while (!operStack.isEmpty())
            copyInput.append(" "+ operStack.pop());
        return copyInput.toString();


    }
}




