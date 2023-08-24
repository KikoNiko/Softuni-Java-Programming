package Exc1;

import java.util.Scanner;

public class LunchBreak {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String tvShowName = scanner.nextLine();
        int episodeLength = Integer.parseInt(scanner.nextLine());
        int breakTime = Integer.parseInt(scanner.nextLine());

        double lunchTime = breakTime / 8.0;
        double quietTime = breakTime / 4.0;

        double totalTime = lunchTime + quietTime + episodeLength;

        if (totalTime <= breakTime) {
            System.out.printf("You have enough time to watch %s and left with %.0f minutes free time.", tvShowName, Math.ceil(breakTime - totalTime));
        } else {
            System.out.printf("You don't have enough time to watch %s, you need %.0f more minutes.", tvShowName, Math.ceil(totalTime - breakTime));
        }
    }
}
