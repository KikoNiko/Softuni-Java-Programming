package Exc;

import java.util.Scanner;

public class TrainTheTrainers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());
        String presentation = scanner.nextLine();
        double sumAvg = 0;
        int presentationCount = 0;

        while (!presentation.equals("Finish")) {
            presentationCount++;
            double sum = 0.0;

            for (int i = 0; i < n; i++) {
                double grade = Double.parseDouble(scanner.nextLine());
                sum += grade;
            }
            double avg = sum / n;
            System.out.printf("%s - %.2f.\n", presentation, avg);
            sumAvg += avg;
            presentation = scanner.nextLine();
        }
        double studentAvg = sumAvg / presentationCount;
        System.out.printf("Student's final assessment is %.2f.", studentAvg);
    }
}
