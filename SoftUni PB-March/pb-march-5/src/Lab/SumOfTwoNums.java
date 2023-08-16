package Lab;

import java.util.Scanner;

public class SumOfTwoNums {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n1 = Integer.parseInt(scanner.nextLine());
        int n2 = Integer.parseInt(scanner.nextLine());
        int num = Integer.parseInt(scanner.nextLine());
        int counter = 0;
        boolean isValid = false;

        for (int i = n1; i <= n2; i++) {
            for (int j = n1; j <= n2; j++) {
                counter++;
                if (i + j == num) {
                    System.out.printf("Combination N:%d (%d + %d = %d)", counter, i, j, num);
                    isValid = true;
                    break;
                }
            }
            if (isValid) {
                break;
            }
        }
        if (!isValid) {
            System.out.printf("%d combinations - neither equals %d", counter, num);
        }

    }
}
