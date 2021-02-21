package RPN;

import java.util.Stack;

public class Evaluator implements Calculator {

    @Override
    public double calculate(String input) {
        ArgumentController checkArgument = new ExpressionUtil();
        PostfixConverter converter = new Converter();
        if (checkArgument.check(input)) {
            return counting(converter.convertToPostfix(input));
        } else throw new IllegalArgumentException("The expression cannot be counted");
    }


    private double counting(String input) {
        double result = 0;
        Stack<Double> temp = new Stack<>();
        StringBuffer number = new StringBuffer();
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == ' ') {
                temp.push(Double.parseDouble(String.valueOf(number)));
                number.setLength(0);
                i++;
            }
            if (!OperationType.IsOperator(String.valueOf(input.charAt(i)))) {
                number.append(input.charAt(i));
                if (i >= input.length()) break;
            } else {
                double firstNumber = temp.pop();
                double secondNumber = temp.pop();

                result = OperationType.getOperand(String.valueOf(input.charAt(i))).getFunction().apply(firstNumber, secondNumber);
                if (Double.isInfinite(result)) throw new ArithmeticException("division by zero");
                temp.push(result);
            }
        }

        return temp.peek();
    }


}
