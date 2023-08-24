package Exc;

import java.util.Scanner;

public class Vacation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double moneyNeeded = Double.parseDouble(scanner.nextLine());
        double moneyAvailable = Double.parseDouble(scanner.nextLine());

        int spendingCount = 0;
        int daysCount = 0;

        while (moneyAvailable < moneyNeeded && spendingCount < 5) {
            String input = scanner.nextLine();
            double money = Double.parseDouble(scanner.nextLine());
            daysCount++;

            if (input.equals("spend")) {
                spendingCount++;
                moneyAvailable -= money;
                if (moneyAvailable < 0) {
                    moneyAvailable = 0;
                }
            } else {
                moneyAvailable += money;
                spendingCount = 0;
            }
        }
        if (spendingCount == 5) {
            System.out.println("You can't save the money.");
            System.out.printf("%d", daysCount);
        }
        if (moneyAvailable >= moneyNeeded) {
            System.out.printf("You saved the money for %d days.", daysCount);
        }
    }
}
