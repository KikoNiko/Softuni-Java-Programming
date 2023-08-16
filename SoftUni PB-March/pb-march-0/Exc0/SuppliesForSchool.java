package Exc0;

import java.util.Scanner;

public class SuppliesForSchool {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int numOfPens = Integer.parseInt(scanner.nextLine());
        int numOfMarkers = Integer.parseInt(scanner.nextLine());
        int detergent = Integer.parseInt(scanner.nextLine());
        double discount = Double.parseDouble(scanner.nextLine()) / 100;

        double fullPrice = numOfPens * 5.80 + numOfMarkers * 7.20 + detergent * 1.20;
        double discountedPrice = fullPrice - (fullPrice * discount);

        System.out.println(discountedPrice);
    }
}
