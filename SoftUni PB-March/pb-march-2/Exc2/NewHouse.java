package Exc2;

import java.util.Scanner;

public class NewHouse {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String flowerType = scanner.nextLine();
        int flowerCount = Integer.parseInt(scanner.nextLine());
        int budget = Integer.parseInt(scanner.nextLine());

        double price = 0.0;
        //"Roses", "Dahlias", "Tulips", "Narcissus", "Gladiolus"
        switch (flowerType) {
            case "Roses":
                price = flowerCount * 5.00;
                if (flowerCount > 80) {
                    price = price - (price * 0.10);
                }
                break;
            case "Dahlias":
                price = flowerCount * 3.80;
                if (flowerCount > 90) {
                    price = price - (price * 0.15);
                }
                break;
            case "Tulips":
                price = flowerCount * 2.80;
                if (flowerCount > 80) {
                    price = price - (price * 0.15);
                }
                break;
            case "Narcissus":
                price = flowerCount * 3.00;
                if (flowerCount < 120) {
                    price = price + (price * 0.15);
                }
                break;
            case "Gladiolus":
                price = flowerCount * 2.50;
                if (flowerCount < 80) {
                    price = price + (price * 0.20);
                }
                break;
        }
        if (price <= budget) {
            System.out.printf("Hey, you have a great garden with %d %s and %.2f leva left.", flowerCount, flowerType, budget - price);
        } else {
            System.out.printf("Not enough money, you need %.2f leva more.", price - budget);
        }
    }
}
