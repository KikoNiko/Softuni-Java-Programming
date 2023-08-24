package Lab;

import java.util.Scanner;

public class Graduation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String name = scanner.nextLine();

        double sum = 0;
        int year = 1;
        int badGrades = 0;

        while (year <= 12) {
            if (badGrades > 1) {
                break;
            }
            double grade = Double.parseDouble(scanner.nextLine());

            if (grade < 4) {
                badGrades++;
                continue;
            }

            sum += grade;
            year ++;
        }

        if (badGrades > 1) {
            System.out.printf("%s has been excluded at %d grade%n", name, year);
        } else {
            double average = sum / 12;
            System.out.printf("%s graduated. Average grade: %.2f%n", name, average);
        }
    }
}
