package Exc;

import java.util.Scanner;

public class EqualSumsEvenOddPosition {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n1 = Integer.parseInt(scanner.nextLine());
        int n2 = Integer.parseInt(scanner.nextLine());

        for (int currentNum = n1; currentNum <= n2; currentNum++) {
            String num = String.valueOf(currentNum);
            int evenSum = 0;
            int oddSum = 0;
            for (int currentDigit = 0; currentDigit < num.length(); currentDigit++) {
                int digit = Integer.parseInt(String.valueOf(num.charAt(currentDigit)));
                if (currentDigit % 2 == 0) {
                    evenSum += digit;
                } else {
                    oddSum += digit;
                }
            }
            if (evenSum == oddSum) {
                System.out.print(num + " ");
            }
        }
    }
}
