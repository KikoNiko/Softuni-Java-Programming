package Exc2;

import java.util.Scanner;

public class OperationsBetweenNumbers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n1 = Integer.parseInt(scanner.nextLine());
        int n2 = Integer.parseInt(scanner.nextLine());
        String operator = scanner.nextLine();

        double result = 0;
        String oddOrEven = "odd";
        boolean isN2Zero = false;

        switch (operator) {
            case "+":
                result = n1 + n2;
                if (result % 2 == 0) {
                    oddOrEven = "even";
                }
                break;
            case "-":
                result = n1 - n2;
                if (result % 2 == 0) {
                    oddOrEven = "even";
                }
                break;
            case "*":
                result = n1 * n2;
                if (result % 2 == 0) {
                    oddOrEven = "even";
                }
                break;
            case "/":
                if (n2 == 0) {
                    isN2Zero = true;
                    break;
                } else {
                    result = (n1 * 1.0) / n2;
                }
                break;
            case "%":
                if (n2 == 0) {
                    isN2Zero = true;
                    break;
                } else {
                    result = n1 % n2;
                }
                break;
        }
        //"{N1} {оператор} {N2} = {резултат} – {even/odd}"
        //"{N1} / {N2} = {резултат}"
        //"{N1} % {N2} = {остатък}"
        //"Cannot divide {N1} by zero"
        if (isN2Zero){
            System.out.printf("Cannot divide %d by zero", n1);
        } else {
            if (operator.equals("+") || operator.equals("-") || operator.equals("*")) {
                System.out.printf("%d %s %d = %.0f - %s", n1, operator, n2, result, oddOrEven);
            } else if (operator.equals("/")) {
                System.out.printf("%d / %d = %.2f", n1, n2, result);
            } else {
                System.out.printf("%d %s %d = %.0f", n1, operator, n2, result);
            }
        }
    }
}
