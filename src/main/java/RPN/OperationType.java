package RPN;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;


public enum OperationType {

    ADDITION("+", 1, (a, b) -> a + b),
    SUBTRACTION("-", 1, (a, b) -> a - b),
    MULTIPLICATION("*", 2, (a, b) -> a * b),
    DIVISTION("/", 2, (a, b) -> a / b);


    private static final Map<String, OperationType> ENUM_OPERATION;

    static {
        Map<String, OperationType> map = new ConcurrentHashMap<>();
        for (OperationType instance : OperationType.values()) {
            map.put(instance.operand, instance);
        }
        ENUM_OPERATION = Collections.unmodifiableMap(map);
    }

    private final String operand;
    private final int priority;
    private final BiFunction<Integer, Integer, Integer> function;


    OperationType(String operand, int priority, BiFunction<Integer, Integer, Integer> function) {
        this.operand = operand;
        this.priority = priority;
        this.function = function;
    }

    public static OperationType getOperand(Character operand) {
        return ENUM_OPERATION.get(operand);
    }
    public static int getPriority(String operand){
        return ENUM_OPERATION.get(operand).priority;
    }
    public BiFunction<Integer, Integer, Integer> getFunction() {
        return function;
    }
    public static boolean IsOperator(String operand){
        return ENUM_OPERATION.containsKey(operand);
    }


}
