import java.util.Scanner;

public class FinalCompetition {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int numOfDancers = Integer.parseInt(scanner.nextLine());
        double points = Double.parseDouble(scanner.nextLine());
        String season = scanner.nextLine();
        String place = scanner.nextLine();

        double moneyEarned = numOfDancers * points;

        if (place.equals("Abroad")) {
            moneyEarned += 0.5 * moneyEarned;
            if (season.equals("summer")) {
                moneyEarned -= 0.10 * moneyEarned;
            } else {
                moneyEarned -= 0.15 * moneyEarned;
            }
        } else {
            if (season.equals("summer")) {
                moneyEarned -= 0.05 * moneyEarned;
            } else {
                moneyEarned -= 0.08 * moneyEarned;
            }
        }
        double charity = moneyEarned * 0.75;
        double moneyPerDancer = (moneyEarned - charity) / numOfDancers;
        System.out.printf("Charity - %.2f\n", charity);
        System.out.printf("Money per dancer - %.2f\n", moneyPerDancer);
    }
}
