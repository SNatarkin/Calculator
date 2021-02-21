package RPN;

import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

@RequiredArgsConstructor
public class EvaluationCalculator implements ICalculator {

    private final IArgumentController argumentController;
    private final PostfixConverter converter;

    @Override
    public double calculate(String input) {
        if (argumentController.check(input)) {
            return counting(converter.convertToPostfix(input));
        } else throw new IllegalArgumentException("The expression cannot be counted");
    }

    private double counting(String input) {
        Stack<Double> temp = new Stack<>();
        final List<String> expression = Arrays.asList(input.split(" "));
        expression.forEach(symbol -> {
            if (isNumeric(symbol)) {
                temp.push(Double.parseDouble(symbol));
            } else if (OperationType.IsOperator(symbol)) {
                double firstNumber = temp.pop();
                double secondNumber = temp.pop();
                temp.push(OperationType.getOperand(symbol).getFunction().apply(secondNumber, firstNumber));
            }
        });
        return temp.peek();
    }

    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
