package Exc2;

import java.util.Scanner;

public class Journey {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double budget = Double.parseDouble(scanner.nextLine());
        String season = scanner.nextLine();

        String destination = "";
        double price = 0.0;
        String campOrHotel = "Hotel";

        if (budget <= 100) {
            destination = "Bulgaria";
            if (season.equals("summer")) {
                price = budget * 0.30;
                campOrHotel = "Camp";
            } else {
                price = budget * 0.70;
            }
        } else if (budget <= 1000) {
            destination = "Balkans";
            if (season.equals("summer")) {
                price = budget * 0.40;
                campOrHotel = "Camp";
            } else {
                price = budget * 0.80;
            }
        } else {
            destination = "Europe";
            price = budget * 0.90;
        }

        System.out.printf("Somewhere in %s\n", destination);
        System.out.printf("%s - %.2f", campOrHotel, price);
    }
}
