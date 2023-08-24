package Lab;

import java.util.Scanner;

public class Travelling {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();

        while (!input.equals("End")){
            String destination = input;
            double budget = Double.parseDouble(scanner.nextLine());
            double sum = 0;

            while (sum < budget) {
                double n = Double.parseDouble(scanner.nextLine());
                sum += n;
            }
            System.out.println("Going to " + destination + '!');
            input = scanner.nextLine();
        }
    }
}
