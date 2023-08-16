import java.util.Scanner;

public class P11Snowballs {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());
        double highestValue = 0;
        int sbSnow = 0;
        int sbTime = 0;
        int sbQuality = 0;

        for (int i = 0; i < n; i++) {
            int snowballSnow = Integer.parseInt(scanner.nextLine());
            int snowballTime = Integer.parseInt(scanner.nextLine());
            int snowballQuality = Integer.parseInt(scanner.nextLine());

            int factor = snowballSnow / snowballTime;
            double snowballValue = Math.pow((factor), snowballQuality);

            if (snowballValue > highestValue) {
                highestValue = snowballValue;
                sbSnow = snowballSnow;
                sbTime = snowballTime;
                sbQuality = snowballQuality;
            }
        }
        System.out.printf("%d : %d = %.0f (%d)", sbSnow, sbTime, highestValue, sbQuality);
    }
}
