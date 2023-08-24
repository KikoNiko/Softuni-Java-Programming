package Exc3;

import java.util.Scanner;

public class TennisRanklist {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int tournaments = Integer.parseInt(scanner.nextLine());
        int startingPoints = Integer.parseInt(scanner.nextLine());

        int tournamentPoints = 0;
        int wins = 0;

        for (int i = 0; i < tournaments; i++) {
            String placeFinished = scanner.nextLine();

            switch (placeFinished) {
                case "W":
                    tournamentPoints += 2000;
                    wins++;
                    break;
                case "F":
                    tournamentPoints += 1200;
                    break;
                case "SF":
                    tournamentPoints += 720;
                    break;

            }
        }

        int totalPoints = startingPoints + tournamentPoints;
        double averagePoints = Math.floor(tournamentPoints * 1.0 / tournaments);
        double percentWinning = wins * 1.0 / tournaments * 100;

        System.out.printf("Final points: %d\n", totalPoints);
        System.out.printf("Average points: %.0f\n", averagePoints);
        System.out.printf("%.2f%%\n", percentWinning);
    }
}
