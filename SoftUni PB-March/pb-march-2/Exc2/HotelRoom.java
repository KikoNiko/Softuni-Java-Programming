package Exc2;

import java.util.Scanner;

public class HotelRoom {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String month = scanner.nextLine();
        int numOfNights = Integer.parseInt(scanner.nextLine());

        double priceOfStudio = 0.00;
        double priceOfApartment = 0.00;

        if (month.equals("May") || month.equals("October")) {
            priceOfStudio = numOfNights * 50.00;
            priceOfApartment = numOfNights * 65.00;
            if (numOfNights > 7 && numOfNights <= 14) {
                priceOfStudio = priceOfStudio - (0.05 * priceOfStudio);
            } else if (numOfNights > 14){
                priceOfStudio = priceOfStudio - (0.30 * priceOfStudio);
            }
        } else if (month.equals("June") || (month.equals("September"))) {
            priceOfStudio = numOfNights * 75.20;
            priceOfApartment = numOfNights * 68.70;
            if (numOfNights > 14) {
                priceOfStudio = priceOfStudio - (0.20 * priceOfStudio);
            }
        } else {
            priceOfStudio = numOfNights * 76.00;
            priceOfApartment = numOfNights * 77.00;
        }
        if (numOfNights > 14) {
            priceOfApartment = priceOfApartment - (0.10 * priceOfApartment);
        }

        System.out.printf("Apartment: %.2f lv.\n", priceOfApartment);
        System.out.printf("Studio: %.2f lv.", priceOfStudio);
    }
}
