package Lab;

import java.util.Scanner;

public class BonusScoringSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int studentsCount = Integer.parseInt(scanner.nextLine());

        int lecturesCount = Integer.parseInt(scanner.nextLine());

        int additionalBonus = Integer.parseInt(scanner.nextLine());

        double maxBonus = 0.0;
        int maxAttendances = 0;
        for (int i = 1; i <= studentsCount; i++) {
            int attendancesCount = Integer.parseInt(scanner.nextLine());
            double currentStudentBonus = calculateBonus(attendancesCount, lecturesCount, additionalBonus);
            if (currentStudentBonus > maxBonus) {
                maxBonus = currentStudentBonus;
                maxAttendances = attendancesCount;
            }
        }
        System.out.printf("Max Bonus: %.0f.%n", maxBonus);
        System.out.printf("The student has attended %d lectures.", maxAttendances);
    }

    public static double calculateBonus(int attendances, int lectures, int bonus) {
        return Math.ceil(1.0 * attendances / lectures * (5 + bonus));
    }
}
