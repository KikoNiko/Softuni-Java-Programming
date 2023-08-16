package Lab3;

import java.util.Scanner;

public class NumberSequence {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int numsCount = Integer.parseInt(scanner.nextLine());
        int minNum = Integer.MAX_VALUE;
        int maxNum = Integer.MIN_VALUE;

        for (int i = 1; i <= numsCount ; i++) {
            int value = Integer.parseInt(scanner.nextLine());

            if (value > maxNum) {
                maxNum = value;
            }
            if (value < minNum) {
                minNum = value;
            }

        }
        System.out.printf("Max number: %d%n", maxNum);
        System.out.printf("Min number: %d", minNum);
    }
}
