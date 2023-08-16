package Lab2;

import java.util.Scanner;

public class WorkingHours {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int hour = Integer.parseInt(scanner.nextLine());
        String day = scanner.nextLine();

        boolean openHours = hour >= 10 && hour <= 18;
        boolean workDay = day.equals("Monday") ||
                day.equals("Tuesday") ||
                day.equals("Wednesday") ||
                day.equals("Thursday") ||
                day.equals("Friday") ||
                day.equals("Saturday");

        if (day.equals("Sunday")) {
            System.out.println("closed");
        }

        if (openHours) {
            if (workDay) {
                System.out.println("open");
            }
        } else {
            System.out.println("closed");
        }
    }
}
