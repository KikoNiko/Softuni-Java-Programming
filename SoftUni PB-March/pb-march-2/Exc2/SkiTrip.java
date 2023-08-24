package Exc2;

import java.util.Scanner;

public class SkiTrip {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int daysOfStay = Integer.parseInt(scanner.nextLine());
        String typeOfPlace = scanner.nextLine();
        String grade = scanner.nextLine();

        double price = 0.00;

        switch (typeOfPlace) {
            case "room for one person":
                price = 18.00 * (daysOfStay - 1);
                break;
            case "apartment":
                price = 25.00 * (daysOfStay - 1);

                if (daysOfStay < 10 ) {
                    price = price - (0.30 * price);
                } else if (daysOfStay <= 15) {
                    price = price - (0.35 * price);
                } else {
                    price = price - (0.50 * price);
                }
                break;
            case "president apartment":
                price = 35.00 * (daysOfStay - 1);

                if (daysOfStay < 10 ) {
                    price = price - (0.10 * price);
                } else if (daysOfStay <= 15) {
                    price = price - (0.15 * price);
                } else {
                    price = price - (0.20 * price);
                }
                break;
        }
        if (grade.equals("positive")) {
            price = price * 1.25;
        } else {
            price = price * 0.90;
        }

        System.out.printf("%.2f", price);
    }
}
