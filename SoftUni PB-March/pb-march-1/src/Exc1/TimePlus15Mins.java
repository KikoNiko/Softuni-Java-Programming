package Exc1;

import java.util.Scanner;

public class TimePlus15Mins {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int hours = Integer.parseInt(scanner.nextLine());
        int minutes = Integer.parseInt(scanner.nextLine());

        int totalMinutes = hours * 60 + minutes + 15;

        hours = totalMinutes / 60;
        minutes = totalMinutes % 60;

        if (hours > 23) {
            hours = 0;
        }

        System.out.printf("%d:%02d", hours, minutes);
    }
}
