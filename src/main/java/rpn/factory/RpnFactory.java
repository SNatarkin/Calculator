package rpn.factory;

import rpn.calculator.EvaluationCalculator;
import rpn.calculator.ICalculator;
import rpn.controller.ArgumentControllerImpl;
import rpn.controller.IArgumentController;
import rpn.converter.Converter;
import rpn.converter.PostfixConverter;

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