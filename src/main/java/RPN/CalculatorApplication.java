package RPN;

import lombok.RequiredArgsConstructor;

import java.util.Scanner;
@RequiredArgsConstructor
public class CalculatorApplication {

    private final ICalculator calculator;
    public CalculatorApplication() {
        this.calculator = new EvaluationCalculator();
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
                double  result = calculator.calculate(input);
                System.out.println(String.format("%.2f\n" , result));

            } catch (ArithmeticException | IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
