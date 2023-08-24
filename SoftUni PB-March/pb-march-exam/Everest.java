import java.util.Scanner;

public class Everest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();
        int daysCount = 1;
        int heightReached = 5364;

        while (!input.equals("END")) {

            if (input.equals("Yes")) {
                daysCount++;
            }
            if (daysCount > 5) {
                break;
            }

            int distanceClimbed = Integer.parseInt(scanner.nextLine());
            heightReached += distanceClimbed;

            if (heightReached >= 8848) {
                break;
            }

            input = scanner.nextLine();
        }

        if (heightReached >= 8848) {
            System.out.printf("Goal reached for %d days!", daysCount);
        } else {
            System.out.println("Failed!");
            System.out.printf("%d", heightReached);
        }
    }
}
