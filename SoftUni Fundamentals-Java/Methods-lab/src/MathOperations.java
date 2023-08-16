import java.text.DecimalFormat;
import java.util.Scanner;

public class MathOperations {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int firstNum = Integer.parseInt(scanner.nextLine());
        String operator = scanner.nextLine();
        int secondNum = Integer.parseInt(scanner.nextLine());

        double result = calculate(firstNum, operator, secondNum);
        System.out.println(new DecimalFormat("0.##").format(result));
    }

    public static double calculate(int a, String operator, int b) {
        double result = 0.0;
        switch (operator) {
            case "*" :
                result = a * b;
                break;
            case "/" :
                result = 1.0 * a / b;
                break;
            case "+" :
                result = a + b;
                break;
            case "-" :
                result = a - b;
                break;
        }
        return result;
    }

}
