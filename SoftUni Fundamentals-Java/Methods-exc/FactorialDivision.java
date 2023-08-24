import java.util.Scanner;

public class FactorialDivision {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int first = Integer.parseInt(scanner.nextLine());
        int second = Integer.parseInt(scanner.nextLine());

        printFactorialDivision(first, second);
    }

    public static long factorial(int n) {
        if (n == 1) {
            return 1;
        }
        long fact = 1;
        for (int i = 2; i <= n; i++) {
            fact *= i;
        }
        return fact;
    }

    public static void printFactorialDivision(int a, int b) {
        double result = 1.0 * factorial(a) / factorial(b);
        System.out.printf("%.2f",result);
    }
}
