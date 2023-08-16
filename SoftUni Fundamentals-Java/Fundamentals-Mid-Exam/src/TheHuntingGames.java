import java.util.Scanner;

public class TheHuntingGames {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int daysOfAdventure = Integer.parseInt(scanner.nextLine());
        int numberOfPlayers = Integer.parseInt(scanner.nextLine());
        double totalEnergy = Double.parseDouble(scanner.nextLine());
        double waterPerDay = Double.parseDouble(scanner.nextLine());
        double foodPerDay = Double.parseDouble(scanner.nextLine());

        double totalWater = waterPerDay * daysOfAdventure * numberOfPlayers;
        double totalFood = foodPerDay * daysOfAdventure * numberOfPlayers;

        for (int i = 1; i <= daysOfAdventure; i++) {
            double energyLoss = Double.parseDouble(scanner.nextLine());
            totalEnergy -= energyLoss;
            if (totalEnergy <= 0) {
                break;
            }
            if (i % 2 == 0) {
                totalWater -= totalWater * 0.30;
                totalEnergy += totalEnergy * 0.05;
            }
            if (i % 3 == 0) {
                double foodEatenToday = totalFood / numberOfPlayers;
                totalFood -= foodEatenToday;
                totalEnergy += totalEnergy * 0.10;
            }
        }

        if (totalEnergy > 0) {
            System.out.printf("You are ready for the quest. You will be left with - %.2f energy!", totalEnergy);
        } else {
            System.out.printf("You will run out of energy. You will be left with %.2f food and %.2f water.", totalFood, totalWater);
        }
    }
}
