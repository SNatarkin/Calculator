package RPN;

public interface IArgumentController {

    /**This method accepts an expression and returns true,
     * if the expression matches the rules imposed to evaluate the expression.
     * The method will return false if the expression contains spaces, duplicate characters,
     * there are letters, characters that are not supported by the calculator,
     * as well as incorrect character placement.
     * And it will issue the appropriate notification.
     * @param input parse expression
     * @return true if the string validation was successful, false if the validation failed
     */
    boolean check(String input);
}
