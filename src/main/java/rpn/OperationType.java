package rpn;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;


public enum OperationType {

    ADDITION("+", 1, Double::sum),
    SUBTRACTION("-", 1, (a, b) -> a - b),
    MULTIPLICATION("*", 2, (a, b) -> a * b),
    DIVISION("/", 2, (a, b) -> {
        if (b == 0) {
            throw new ArithmeticException("division by zero");
        }
        return a / b;
    });

    private static final Map<String, OperationType> ENUM_OPERATION;
    private static final String OPERATORS;

    static {
        Map<String, OperationType> map = new ConcurrentHashMap<>();
        StringBuffer operators = new StringBuffer();
        for (OperationType instance : OperationType.values()) {
            map.put(instance.operand, instance);
            operators.append(instance.operand);
        }
        OPERATORS = String.valueOf(operators);
        ENUM_OPERATION = Collections.unmodifiableMap(map);
    }

    private final String operand;
    private final int priority;
    private final BiFunction<Double, Double, Double> function;

    OperationType(String operand, int priority, BiFunction<Double, Double, Double> function) {
        this.operand = operand;
        this.priority = priority;
        this.function = function;
    }

    public static OperationType getOperand(String operand) {
        return ENUM_OPERATION.get(operand);
    }

    public static int getPriority(String operand) {
        return ENUM_OPERATION.get(operand).priority;
    }

    public static boolean IsOperator(String operand) {
        return ENUM_OPERATION.containsKey(operand);
    }

    public static String getOperators() {
        return OPERATORS;
    }

    public BiFunction<Double, Double, Double> getFunction() {
        return function;
    }

}
