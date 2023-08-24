package Exc1;

import java.util.Scanner;

public class GodzillaVsKong {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double movieBudget = Double.parseDouble(scanner.nextLine());
        int extras = Integer.parseInt(scanner.nextLine());
        double extrasClothes = Double.parseDouble(scanner.nextLine());

        double decore = 0.10 * movieBudget;

        if (extras > 150) {
            extrasClothes = extrasClothes - (0.10 * extrasClothes);
        }
        double extrasExpenses = extrasClothes * extras;

        if ((extrasExpenses + decore) > movieBudget) {
            System.out.println("Not enough money!");
            System.out.printf("Wingard needs %.2f leva more.", (extrasExpenses + decore) - movieBudget);
        } else {
            System.out.println("Action!");
            System.out.printf("Wingard starts filming with %.2f leva left.", movieBudget - (extrasExpenses + decore));
        }

    }
}
