package Exc0;

import java.util.Scanner;

public class FoodDelivery {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int chickenMenu = Integer.parseInt(scanner.nextLine());
        int fishMenu = Integer.parseInt(scanner.nextLine());
        int veggieMenu = Integer.parseInt(scanner.nextLine());

        double totalMenuPrice = chickenMenu * 10.35 + fishMenu * 12.40 + veggieMenu * 8.15;
        double dessert = 0.20 * totalMenuPrice;
        double totalPrice = totalMenuPrice + dessert + 2.50;

        System.out.println(totalPrice);
    }
}
