package Lab0;

import java.util.Scanner;

public class YardGreening {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double yards = Double.parseDouble(scanner.nextLine());

        double finalPrice = yards * 7.61;
        double discount = 0.18 * finalPrice;

        System.out.printf("The final price is: %.2f lv.", finalPrice - discount);
        System.out.println();
        System.out.printf("The discount is: %.2f lv.", discount);

    }
}
