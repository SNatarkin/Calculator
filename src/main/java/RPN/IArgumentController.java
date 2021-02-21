package rpn;

public interface IArgumentController {

    /**
     * @param input parse expression
     * @return true if the string validation was successful, false if the validation failed
     */
    boolean check(String input);
}
