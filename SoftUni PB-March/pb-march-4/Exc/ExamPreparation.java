package Exc;

import java.util.Scanner;

public class ExamPreparation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int badGradesAllowed = Integer.parseInt(scanner.nextLine());

        int badGrades = 0;
        double sumGrades = 0.0;
        int gradesCount = 0;
        String lastTask = "";

        String input = scanner.nextLine();

        while (!input.equals("Enough")) {

            lastTask = input;

            int grade = Integer.parseInt(scanner.nextLine());
            gradesCount++;
            sumGrades += grade;

            if (grade <= 4) {
                badGrades++;
            }

            if (badGrades == badGradesAllowed){
                System.out.printf("You need a break, %d poor grades.", badGrades);
                break;
            }

            input = scanner.nextLine();
        }

        if (input.equals("Enough")){
            System.out.printf("Average score: %.2f\n", sumGrades / gradesCount);
            System.out.printf("Number of problems: %d\n", gradesCount);
            System.out.printf("Last problem: %s\n", lastTask);
        }
    }
}
