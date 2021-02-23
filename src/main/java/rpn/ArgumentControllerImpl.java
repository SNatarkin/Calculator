package rpn;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ArgumentControllerImpl implements IArgumentController {

    @Override
    public boolean check(String input) {
        return isLetter(input) && isWhitespace(input) && checkPlaceOperands(input) && checkOperands(input);
    }

    private boolean isLetter(String input) {
        if (input.chars().anyMatch(Character::isLetter)) {
            System.out.println(String.format("Expression cannot be composed of characters [%s]", input));
            return false;
        }
        return true;
    }

    private boolean isWhitespace(String input) {
        if (input.chars().anyMatch(Character::isWhitespace)) {
            System.out.println(String.format("Expressions cannot contain empty spaces [%s]", input));
            return false;
        }
        return true;
    }

    private boolean checkOperands(String input) {
        String operands = input.replaceAll("[0-9.]", "");
        for (int i = 0; i < operands.length(); i++) {
            final String expectedOperand = String.valueOf(operands.charAt(i));
            if (!OperationType.IsOperator(expectedOperand)) {
                System.out.println(String.format("The operand you entered does not exist [%s]", expectedOperand));
                return false;
            }
        }
        if (countNumbers(input) - operands.length() != 1) {
            System.out.println("The number of operands cannot be equal to or greater than the number of digits");
            return false;
        }
        return true;
    }

    private boolean checkPlaceOperands(String input) {
        if (!(Character.isDigit(input.charAt(0)) && Character.isDigit(input.charAt(input.length() - 1)))) {
            System.out.println("Expression cannot start or end with an operand,only digits");
            return false;
        }
        return true;
    }

    private int countNumbers(String input) {
        Pattern p = Pattern.compile("\\d+(\\.\\d*)?");
        Matcher m = p.matcher(input);
        final List<String> numbers = new ArrayList<>();
        while (m.find()) {
            numbers.add(m.group());
        }
        return numbers.size();
    }
}



