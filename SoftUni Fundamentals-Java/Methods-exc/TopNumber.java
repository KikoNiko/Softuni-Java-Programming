import java.util.Arrays;
import java.util.Scanner;

public class TopNumber {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());

        for (int i = 8; i <= n; i++) {
            printTopNumber(i);
        }
    }

    public static boolean isDivisibleByEight(int num) {
        String strNum = String.valueOf(num);
        int [] digits = Arrays.stream(strNum.split(""))
                .mapToInt(Integer::parseInt)
                .toArray();
        int sumDigits = 0;
        for (int digit : digits) {
            sumDigits += digit;
        }
        if (sumDigits % 8 == 0) {
            return true;
        }
        return false;
    }

    public static boolean hasOddDigit(int n) {
        while (n != 0) {
            int lastDigit = n % 10;
            if (lastDigit % 2 != 0) {
                return true;
            }
            n /= 10;
        }
        return false;
    }

    public static void printTopNumber(int n) {
        if (isDivisibleByEight(n) && hasOddDigit(n)) {
            System.out.println(n);
        }
    }

}
