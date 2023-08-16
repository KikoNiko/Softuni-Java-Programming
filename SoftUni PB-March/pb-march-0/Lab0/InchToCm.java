package Lab0;

import java.util.Scanner;

public class InchToCm {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double inches = scanner.nextDouble();
        System.out.println(inches * 2.54);
    }
}
