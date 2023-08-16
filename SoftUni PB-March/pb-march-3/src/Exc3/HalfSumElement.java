package Exc3;

import java.util.Scanner;

public class HalfSumElement {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());
        int max = Integer.MIN_VALUE;
        int sum = 0;

        for (int i = 1; i <= n; i++) {
            int num = Integer.parseInt(scanner.nextLine());
            sum += num;
            if (num > max) {
                max = num;
            }
        }

        if (sum - max == max) {
            System.out.println("Yes");
            System.out.printf("Sum = %d", sum - max);
        } else {
            int diff = Math.abs(sum - max - max);
            System.out.println("No");
            System.out.printf("Diff = %d", diff);
        }
    }
}
