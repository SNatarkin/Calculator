package RPN;

public class RpnFactory {

    public static ICalculator getDefaultCalculator() {
        return new EvaluationCalculator(getDefaultArgumentController(), getDefaultConverter());
    }

    public static Converter getDefaultConverter() {
        return new PostfixConverter();
    }

    public static IArgumentController getDefaultArgumentController() {
        return new ArgumentControllerImpl();
    }
}