package Exc0;

import java.util.Scanner;

public class DepositCalc {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double depositAmount = Double.parseDouble(scanner.nextLine());
        int depositPeriod = Integer.parseInt(scanner.nextLine());
        double interest = Double.parseDouble(scanner.nextLine()) / 100;

        double totalAmount = depositAmount + depositPeriod * ((depositAmount * interest) / 12);

        System.out.println(totalAmount);
    }
}
