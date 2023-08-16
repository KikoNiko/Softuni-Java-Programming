package Lab;

import java.util.Scanner;

public class MinNumber {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();
        int min = Integer.MAX_VALUE;

        while (!input.equals("Stop")) {
            int n = Integer.parseInt(input);
            if (n < min) {
                min = n;
            }
            input = scanner.nextLine();
        }
        System.out.println(min);
    }
}
