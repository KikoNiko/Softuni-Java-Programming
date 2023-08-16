package Exc;

import java.util.Scanner;

public class SpecialNumbers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());

        for (int currentNum = 1111; currentNum <= 9999; currentNum++) {

            String strNum = String.valueOf(currentNum);
            boolean isSpecial = true;

            for (int currentDigit = 0; currentDigit < strNum.length(); currentDigit++) {

                int digit = Integer.parseInt(strNum.charAt(currentDigit) + "");

                if (digit == 0) {
                    isSpecial = false;
                    break;
                }

                if (n % digit != 0) {
                    isSpecial = false;
                    break;
                }
            }

            if (isSpecial) {
                System.out.print(currentNum + " ");
            }
        }
    }
}
