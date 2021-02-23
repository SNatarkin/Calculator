package rpn.api;

import lombok.RequiredArgsConstructor;
import rpn.calculator.ICalculator;
import rpn.factory.RpnFactory;

import java.util.Scanner;

@RequiredArgsConstructor
public class CalculatorApplication {

    private final ICalculator calculator;

    public CalculatorApplication() {
        this.calculator = RpnFactory.getDefaultCalculator();
    }

    public static void main(String[] args) {
        new CalculatorApplication().run();
    }

    void run() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to my expression calculator, if you want to end the program enter: exit");
        while (true) {
            System.out.println("Enter expression");
            String input = sc.nextLine();
            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Thanks for using the calculator, goodbye");
                break;
            }
            try {
                double result = calculator.calculate(input);
                System.out.println(String.format("%.2f\n", result));

            } catch (ArithmeticException | IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
