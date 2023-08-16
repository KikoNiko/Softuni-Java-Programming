package Exc;

import java.util.Scanner;

public class SumPrimeNonPrime {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int primeSum = 0;
        int nonPrimeSum = 0;

        String input = scanner.nextLine();

        while (!input.equals("stop")) {
            int num = Integer.parseInt(input);

            if (num < 0) {
                System.out.println("Number is negative.");
                input = scanner.nextLine();
                continue;
            }
            boolean isNonPrime = false;
            for (int i = 2; i < num; i++) {
                if (num % i == 0) {
                    isNonPrime = true;
                    break;
                }
            }
            if (isNonPrime) {
                nonPrimeSum += num;
            } else {
                primeSum += num;
            }
            input = scanner.nextLine();
        }
        System.out.printf("Sum of all prime numbers is: %d%n", primeSum);
        System.out.printf("Sum of all non prime numbers is: %d", nonPrimeSum);
    }
}
