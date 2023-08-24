import java.util.Scanner;

public class P06StrongNumber {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int num = Integer.parseInt(scanner.nextLine());
        int sumFact = 0;
        int n = num;

        while (num > 0) {
            int lastDigit = num % 10;
            int fact = 1;

            for (int i = 1; i <= lastDigit; i++) {
                fact *= i;
            }
            sumFact += fact;
            num /= 10;
        }
        if (sumFact == n) {
            System.out.println("yes");
        } else {
            System.out.println("no");
        }

    }
}
