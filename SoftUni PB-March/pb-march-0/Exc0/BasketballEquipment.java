package Exc0;

import java.util.Scanner;

public class BasketballEquipment {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int yearlyRate = Integer.parseInt(scanner.nextLine());

        double sneakersPrice = yearlyRate * (1 - 0.40);
        double outfitPrice = sneakersPrice * (1 - 0.20);
        double ballPrice = outfitPrice * 0.25;
        double accessories = ballPrice * 0.20;

        double totalPrice = yearlyRate + sneakersPrice + outfitPrice + ballPrice + accessories;

        System.out.println(totalPrice);
    }
}
