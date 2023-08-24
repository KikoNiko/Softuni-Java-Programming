import java.util.Arrays;
import java.util.Scanner;

public class ZigZagArrays {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());
        int [] firstArr = new int[n];
        int [] secondArr = new int[n];

        for (int i = 0; i < n; i++) {
            String input = scanner.nextLine();
            int firstNum = Integer.parseInt(input.split(" ")[0]);
            int secondNum = Integer.parseInt(input.split(" ")[1]);

            if (i % 2 == 0) {
                firstArr[i] = firstNum;
                secondArr[i] = secondNum;
            } else {
                firstArr[i] = secondNum;
                secondArr[i] = firstNum;
            }
        }
        for (int el : firstArr) {
            System.out.print(el + " ");
        }
        System.out.println();

        for (int el : secondArr) {
            System.out.print(el + " ");
        }
    }
}
