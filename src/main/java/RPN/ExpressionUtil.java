package RPN;

import java.util.Arrays;
import java.util.regex.Pattern;

public class ExpressionUtil implements ArgrumentController {

    @Override
    public boolean check(String input) {
        return isLetter(input) & isWhitespace(input) & checkOperand(input);
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

    private boolean checkOperand(String input) {
        String operands = input.replaceAll("[0-9.]", "");
        if ((Character.isDigit(input.charAt(0)) && Character.isDigit(input.charAt(input.length() - 1)))) {
            for (int i = 0; i < operands.length(); i++) {
                if (OperationType.IsOperator(String.valueOf(operands.charAt(i)))) {
                } else {
                    throw new IllegalArgumentException("The operand you entered does not exist");
                }
            }
            Object[] digits = Arrays.stream(input.trim().split("[" + Pattern.quote(OperationType.getOperators()) + "]")).
                    filter(s -> !s.equals("")).
                    map(s -> s.trim()).toArray();
            if (digits.length - operands.length() != 1) {
                throw new IllegalArgumentException("The number of operands cannot be equal to or greater than the number of digits");
            }

        } else {
            throw new IllegalArgumentException("Expression cannot start or end with an operand");

        }
        return true;

    }


}
