import java.util.Arrays;
import java.util.Scanner;

public class SumEvenNumbers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();

        int[] numArr = Arrays
                .stream(input.split(" "))
                .mapToInt(Integer::parseInt) //e -> Integer.parseInt(e)
                .toArray();

        int sumEven = 0;
        for (int i = 0; i < numArr.length; i++) {
            if (numArr[i] % 2 == 0) {
                sumEven += numArr[i];
            }
        }
        System.out.println(sumEven);
    }
}
