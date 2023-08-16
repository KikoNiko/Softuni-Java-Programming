package Exc1;

import java.util.Scanner;

public class WordSwimmingRecord {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double recordTime = Double.parseDouble(scanner.nextLine());
        double distance = Double.parseDouble(scanner.nextLine());
        double speedInSec = Double.parseDouble(scanner.nextLine());

        double rawTime = distance * speedInSec;
        double totalTime = ((Math.floor(distance / 15)) * 12.5) + rawTime;

        if (totalTime < recordTime) {
            System.out.printf("Yes, he succeeded! The new world record is %.2f seconds.", totalTime);
        } else {
            System.out.printf("No, he failed! He was %.2f seconds slower.", totalTime - recordTime);
        }
    }
}
