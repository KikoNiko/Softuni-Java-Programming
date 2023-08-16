package Exc0;

import java.util.Scanner;

public class Repainting {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int nylon = Integer.parseInt(scanner.nextLine());
        int paint = Integer.parseInt(scanner.nextLine());
        int paintThinner = Integer.parseInt(scanner.nextLine());
        int hoursOfWork = Integer.parseInt(scanner.nextLine());

        double nylonTotal = (nylon + 2) * 1.50;
        double paintTotal = (1.10 * paint) * 14.50;
        double materialsTotal = nylonTotal + paintTotal + paintThinner * 5 + 0.40;
        double workersFee = hoursOfWork * (materialsTotal * 0.30);

        double totalAmount = materialsTotal + workersFee;

        System.out.println(totalAmount);
    }
}
