package Exc2;

import java.util.Scanner;

public class OnTimeForExam {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int hourOfExam = Integer.parseInt(scanner.nextLine());
        int minuteOfExam = Integer.parseInt(scanner.nextLine());
        int hourOfArrival = Integer.parseInt(scanner.nextLine());
        int minuteOfArrival = Integer.parseInt(scanner.nextLine());

        int examTime = hourOfExam * 60 + minuteOfExam;
        int arrivalTime = hourOfArrival * 60 + minuteOfArrival;
        int diff = Math.abs(examTime - arrivalTime);

        if (examTime < arrivalTime) {
            System.out.println("Late");
            int hour = diff / 60;
            int minutes = diff % 60;
            if (diff < 60) {
                System.out.printf("%d minutes after the start", minutes);
            } else {
                System.out.printf("%d:%02d hours after the start", hour, minutes);
            }
        }

        if (examTime >= arrivalTime && diff <= 30) {
            System.out.println("On time");
            if (diff != 0) {
                System.out.printf("%d minutes before the start", diff);
            }
        }

        if (examTime > arrivalTime && diff > 30) {
            System.out.println("Early");
            int hour = diff / 60;
            int minutes = diff % 60;
            if (diff < 60) {
                System.out.printf("%d minutes before the start", minutes);
            } else {
                System.out.printf("%d:%02d hours before the start", hour, minutes);
            }
        }
    }
}
