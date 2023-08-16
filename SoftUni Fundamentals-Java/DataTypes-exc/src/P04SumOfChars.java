import java.util.Scanner;

public class P04SumOfChars {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());

        int sumChars = 0;

        for (int i = 1; i <= n; i++) {
            char c = scanner.nextLine().charAt(0);
            sumChars += c;
        }
        System.out.println("The sum equals: " + sumChars);
    }
}
