package Exc;

import java.util.Scanner;

public class SoftUniReception {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int firstEmployee = Integer.parseInt(scanner.nextLine());
        int secondEmployee = Integer.parseInt(scanner.nextLine());
        int thirdEmployee = Integer.parseInt(scanner.nextLine());

        int efficiencyPerHour = firstEmployee + secondEmployee + thirdEmployee;

        int remainingStudents = Integer.parseInt(scanner.nextLine());

        int timeNeeded = 0;
        while (remainingStudents > 0) {
            timeNeeded++;

            if (timeNeeded % 4 == 0) {
                continue;
            }
            remainingStudents -= efficiencyPerHour;
        }

        System.out.printf("Time needed: %dh.%n", timeNeeded);
    }
}
