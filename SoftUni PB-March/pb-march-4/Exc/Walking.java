package Exc;

import java.util.Scanner;

public class Walking {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();
        int sum = 0;
        int goal = 10000;

        while (!input.equals("Going home")) {
            int steps = Integer.parseInt(input);
            sum += steps;
            if (sum >= goal) {
                break;
            }
            input = scanner.nextLine();
        }
        if (input.equals("Going home")) {
            int steps = Integer.parseInt(scanner.nextLine());
            sum += steps;
        }
        if (sum >= goal) {
            System.out.println("Goal reached! Good job!");
            System.out.printf("%d steps over the goal!", sum - goal);
        } else {
            System.out.printf("%d more steps to reach goal.", goal - sum);
        }
    }
}
