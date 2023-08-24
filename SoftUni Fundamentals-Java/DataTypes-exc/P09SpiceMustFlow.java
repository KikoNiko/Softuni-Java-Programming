import java.util.Scanner;

public class P09SpiceMustFlow {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int startingYield = Integer.parseInt(scanner.nextLine());
        int days = 0;
        int totalYield = 0;

        while (startingYield >= 100) {
            int spiceLeft = startingYield - 26;
            totalYield += spiceLeft;
            startingYield -= 10;
            days++;
        }

        if (totalYield >= 26) {
            totalYield -= 26;
        }

        System.out.println(days);
        System.out.println(totalYield);
    }
}
