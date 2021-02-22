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
        while (true) {
            System.out.println("");
            String input = sc.nextLine();
            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Thanks for using the calculator, goodbye");
                break;
            }
            try {
               double result =  calculator.calculate(input);
                System.out.println("Результат " + result);
                System.out.println("if you want to end the program enter: exit");

            } catch (ArithmeticException | IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
