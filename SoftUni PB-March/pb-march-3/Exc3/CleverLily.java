package Exc3;

import java.util.Scanner;

public class CleverLily {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int age = Integer.parseInt(scanner.nextLine());
        double wmPrice = Double.parseDouble(scanner.nextLine());
        int toyPrice = Integer.parseInt(scanner.nextLine());

        int toysCount = 0;
        double lilyMoney = 0.00;
        double giftMoney = 10.00;

        for (int i = 1; i <= age ; i++) {
            if (i % 2 == 0) {
                lilyMoney += giftMoney;
                giftMoney += 10.00;
                lilyMoney -= 1.00;
            } else {
                toysCount++;
            }
        }
        lilyMoney += toysCount * toyPrice;

        if (lilyMoney >= wmPrice) {
            System.out.printf("Yes! %.2f", lilyMoney - wmPrice);
        } else {
            System.out.printf("No! %.2f", wmPrice - lilyMoney);
        }
    }
}
