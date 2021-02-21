package RPN;

import java.util.Arrays;
import java.util.regex.Pattern;

public class ExpressionUtil implements ArgumentController {

    @Override
    public boolean check(String input) {
        return isLetter(input) & isWhitespace(input) & checkPlaceOperands(input) & checkOperands(input);
    }


    private boolean isLetter(String input) {
        if (input.chars().anyMatch(Character::isLetter)) {
            throw new IllegalArgumentException("Expression cannot be composed of characters");
        }
        return true;


    }

    private boolean isWhitespace(String input) {
        if (input.chars().anyMatch(Character::isWhitespace)) {
            throw new IllegalArgumentException("Expressions cannot contain empty spaces");
        }
        return true;
    }

    private boolean checkOperands(String input) {
        String operands = input.replaceAll("[0-9.]", "");
        for (int i = 0; i < operands.length(); i++) {
            if (OperationType.IsOperator(String.valueOf(operands.charAt(i)))) {
            } else {
                throw new IllegalArgumentException("The operand you entered does not exist");
            }
        }
        if (getNumbers(input).length - operands.length() != 1) {
            throw new IllegalArgumentException("The number of operands cannot be equal to or greater than the number of digits");
        }


        return true;

    }

    private boolean checkPlaceOperands(String input) {
        if ((Character.isDigit(input.charAt(0)) && Character.isDigit(input.charAt(input.length() - 1)))) {
            return true;
        } else throw new IllegalArgumentException("Expression cannot start or end with an operand");
    }

    private String[] getNumbers(String input) {
        Object[] arrayInput = Arrays.stream(input.trim().split("[" + Pattern.quote(OperationType.getOperators()) + "]")).
                filter(s -> !s.equals("")).
                map(s -> s.trim()).toArray();
        return Arrays.copyOf(arrayInput, arrayInput.length, String[].class);
    }

}
