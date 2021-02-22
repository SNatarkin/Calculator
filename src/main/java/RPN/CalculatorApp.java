package RPN;

import lombok.RequiredArgsConstructor;

import java.util.Scanner;
@RequiredArgsConstructor
public class CalculatorApp {

    private final ICalculator calculator;
    public CalculatorApp() {
        this.calculator = new EvaluationCalculator();
    }
    void run() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            String input = sc.nextLine();
            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Thanks for using the calculator, goodbye");
                break;
            }
            try {
               double result =  calculator.calculate(input);
                System.out.println("Результат " + result);

            } catch (ArithmeticException e) {
                System.out.println(e.getMessage());
            } catch (IllegalArgumentException e){
                System.out.println(e.getMessage());
            }
        }
    }
}
