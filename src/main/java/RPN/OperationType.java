package RPN;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;


public enum OperationType {

    ADDITION('+', 1, (a, b) -> a + b),
    SUBTRACTION('-', 1, (a, b) -> a - b),
    MULTIPLICATION('*', 2, (a, b) -> a * b),
    DIVISTION('/', 2, (a, b) -> a / b);


    private static final Map<Character, OperationType> ENUM_OPERATION;

    static {
        Map<Character, OperationType> map = new ConcurrentHashMap<>();
        for (OperationType instance : OperationType.values()) {
            map.put(instance.operand, instance);
        }
        ENUM_OPERATION = Collections.unmodifiableMap(map);
    }

    private final char operand;
    private final int priority;
    private final BiFunction<Integer, Integer, Integer> function;


    OperationType(char operand, int priority, BiFunction<Integer, Integer, Integer> function) {
        this.operand = operand;
        this.priority = priority;
        this.function = function;
    }

    public static OperationType getOperand(Character operand) {
        return ENUM_OPERATION.get(operand);
    }
    public static int getPriority(Character operand){
        return ENUM_OPERATION.get(operand).priority;
    }
    public BiFunction<Integer, Integer, Integer> getFunction() {
        return function;
    }
    public static boolean IsOperator(Character operand){
        return ENUM_OPERATION.containsKey(operand);
    }


}
