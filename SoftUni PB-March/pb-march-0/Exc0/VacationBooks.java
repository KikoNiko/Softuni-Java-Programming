package Exc0;

import java.util.Scanner;

public class VacationBooks {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int numOfPages = Integer.parseInt(scanner.nextLine());
        int pagesPerHour = Integer.parseInt(scanner.nextLine());
        int numOfDays = Integer.parseInt(scanner.nextLine());

        int numOfHours = numOfPages / pagesPerHour / numOfDays;

        System.out.println(numOfHours);
    }
}
