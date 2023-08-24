package Exc1;

import java.util.Scanner;

public class ToyShop {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double vacationPrice = Double.parseDouble(scanner.nextLine());
        int puzzles = Integer.parseInt(scanner.nextLine());
        int dolls = Integer.parseInt(scanner.nextLine());
        int bears = Integer.parseInt(scanner.nextLine());
        int minions = Integer.parseInt(scanner.nextLine());
        int trucks = Integer.parseInt(scanner.nextLine());

        double orderPrice = (puzzles * 2.60) + (dolls * 3) + (bears * 4.10) + (minions * 8.20) + (trucks * 2);

        int toysCount = puzzles + dolls + bears + minions + trucks;

        if (toysCount >= 50) {
            orderPrice = orderPrice - (0.25 * orderPrice);
        }

        orderPrice = orderPrice - (0.10 * orderPrice);

        if (vacationPrice <= orderPrice) {
            System.out.printf("Yes! %.2f lv left.", orderPrice - vacationPrice);
        } else {
            System.out.printf("Not enough money! %.2f lv needed.", vacationPrice - orderPrice);
        }

    }
}
