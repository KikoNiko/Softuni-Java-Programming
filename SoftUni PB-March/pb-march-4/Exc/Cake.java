package Exc;

import java.util.Scanner;

public class Cake {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int x = Integer.parseInt(scanner.nextLine());
        int y = Integer.parseInt(scanner.nextLine());
        int area = x * y;
        int sum = 0;
        String input = scanner.nextLine();

        while (!input.equals("STOP")) {
            int pieces = Integer.parseInt(input);
            sum += pieces;
            if (sum >= area) {
                break;
            }
            input = scanner.nextLine();
        }

        if (sum >= area) {
            System.out.printf("No more cake left! You need %d pieces more.", sum - area);
        } else {
            System.out.printf("%d pieces are left.", area - sum);
        }
    }
}
