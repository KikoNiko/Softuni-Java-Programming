package Lab3;

import java.util.Scanner;

public class LeftAndRightSum {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());
        int left = 0;
        int right = 0;

        for (int i = 1; i <= 2 * n / 2; i++) {
            int leftNum = Integer.parseInt(scanner.nextLine());
            left += leftNum;
        }
        for (int i = 1; i <= 2 * n / 2; i++) {
            int rightNum = Integer.parseInt(scanner.nextLine());
            right += rightNum;
        }

        int diff = Math.abs(left - right);
        if (diff == 0) {
            System.out.printf("Yes, sum = %d", left);
        } else {
            System.out.printf("No, diff = %d", diff);
        }
    }
}
