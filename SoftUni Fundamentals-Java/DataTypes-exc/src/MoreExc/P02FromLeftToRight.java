package MoreExc;

import java.util.Scanner;

public class P02FromLeftToRight {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());

        for (int i = 1; i <= n; i++) {
            String input = scanner.nextLine();
            String left = "";
            String right = "";
            int index = 0;
            for (int j = 0; j < input.length(); j++) {
                if (input.charAt(j) != 32) {
                    left += input.charAt(j);
                } else {
                    index = j + 1;
                    break;
                }
            }
            for (int j = index; j < input.length(); j++) {
                right += input.charAt(j);
            }

            long leftNum = Long.parseLong(left);
            long rightNum = Long.parseLong(right);

            long biggerNum = Math.max(leftNum, rightNum);

            int sumDigits = 0;
            while (biggerNum != 0) {
                sumDigits += biggerNum % 10;
                biggerNum /= 10;
            }
            System.out.println(Math.abs(sumDigits));
        }
    }
}
