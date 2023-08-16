import java.util.Arrays;
import java.util.Scanner;

public class MagicSum {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int [] arr = Arrays.stream(scanner.nextLine()
                .split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        int magicNum = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < arr.length - 1; i++) {
            int currentNum = arr[i];
            for (int j = i + 1; j < arr.length; j++) {
                if (currentNum + arr[j] == magicNum) {
                    System.out.printf("%d %d%n", currentNum, arr[j]);
                }
            }
        }
    }
}
