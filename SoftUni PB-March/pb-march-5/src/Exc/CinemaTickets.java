package Exc;

import java.util.Scanner;

public class CinemaTickets {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String movieTitle = scanner.nextLine();
        double students = 0.00;
        double standards = 0.00;
        double kids = 0.00;
        int ticketsSold = 0;

        while (!movieTitle.equals("Finish")) {

            int totalFreeTickets = Integer.parseInt(scanner.nextLine());

            int ticketCount = 0;

            for (int i = 0; i < totalFreeTickets; i++) {
                String ticketType = scanner.nextLine();
                if (ticketType.equals("End")) {
                    break;
                }
                ticketCount++;
                ticketsSold++;
                if (ticketType.equals("student")) {
                    students++;
                } else if (ticketType.equals("standard")) {
                    standards++;
                } else {
                    kids++;
                }
            }
            double percentFull = ((ticketCount * 1.0) / totalFreeTickets) * 100;
            System.out.printf("%s - %.2f%% full.\n", movieTitle, percentFull);

            movieTitle = scanner.nextLine();
        }

        System.out.println("Total tickets: " + ticketsSold);
        System.out.printf("%.2f%% student tickets.\n", (students / ticketsSold) * 100);
        System.out.printf("%.2f%% standard tickets.\n", (standards / ticketsSold) * 100);
        System.out.printf("%.2f%% kids tickets.", (kids / ticketsSold) * 100);
    }
}
