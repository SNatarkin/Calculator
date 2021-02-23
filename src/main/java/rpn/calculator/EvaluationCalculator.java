package rpn.calculator;

import lombok.RequiredArgsConstructor;
import rpn.controller.IArgumentController;
import rpn.converter.Converter;
import rpn.operators.OperationType;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

@RequiredArgsConstructor
public class EvaluationCalculator implements ICalculator {

    private final IArgumentController argumentController;
    private final Converter converter;

    @Override
    public double calculate(String input) throws IllegalArgumentException , ArithmeticException{
        if (argumentController.check(input)) {
            return counting(converter.convertToPostfix(input));
        } else throw new IllegalArgumentException("The expression cannot be counted");
    }

    private double counting(String postfixExpression) {
        Stack<Double> temp = new Stack<>();
        final List<String> expression = Arrays.asList(postfixExpression.split(" "));
        expression.forEach(symbol -> {
            if (!isNumeric(symbol) & !OperationType.IsOperator(symbol)) {
                throw new IllegalArgumentException(String.format("Lists cannot contain spaces or other characters [%s]", symbol));
            }
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

    private boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
