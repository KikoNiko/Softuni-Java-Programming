package Exc3;

import java.util.Scanner;

public class Oscars {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String actorName = scanner.nextLine();
        double academyPoints = Double.parseDouble(scanner.nextLine());
        int nJudges = Integer.parseInt(scanner.nextLine());

        double actorPoints = 0.0;
        actorPoints += academyPoints;

        for (int i = 1; i <= nJudges ; i++) {
            String judgeName = scanner.nextLine();
            double judgePoints = Double.parseDouble(scanner.nextLine());
            actorPoints += judgeName.length() * judgePoints / 2;

            if (actorPoints > 1250.5) {
                System.out.printf("Congratulations, %s got a nominee for leading role with %.1f!", actorName, actorPoints);
                break;
            }
        }
        if (actorPoints < 1250.5) {
            System.out.printf("Sorry, %s you need %.1f more!", actorName, 1250.5 - actorPoints);
        }
    }
}
