import java.util.Arrays;
import java.util.Scanner;

public class EqualArrays {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] firstArr = Arrays
                .stream(scanner.nextLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int[] secondArr = Arrays
                .stream(scanner.nextLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int maxLength = Math.max(firstArr.length, secondArr.length);
        int sum = 0;
        boolean isIdentical = true;

        for (int i = 0; i < maxLength; i++) {
            if (firstArr[i] != secondArr[i]) {
                System.out.printf("Arrays are not identical. Found difference at %d index.", i);
                isIdentical = false;
                break;
            } else {
                sum += firstArr[i];
            }
        }
        if (isIdentical) {
            System.out.printf("Arrays are identical. Sum: %d", sum);
        }
    }
}
