package Exc2;

import java.util.Scanner;

public class Cinema {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String projectionType = scanner.nextLine();
        int rows = Integer.parseInt(scanner.nextLine());
        int columns = Integer.parseInt(scanner.nextLine());

        double income = rows * columns;

        if (projectionType.equals("Premiere")) {
            income *= 12;
        } else if (projectionType.equals("Normal")) {
            income *= 7.5;
        } else if (projectionType.equals("Discount")) {
            income *= 5;
        }

        System.out.printf("%.2f leva", income);
    }
}
