import java.util.Arrays;
import java.util.Scanner;

public class EqualSums {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int [] arr = Arrays.stream(scanner.nextLine()
                .split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        boolean isEqual = false;

        for (int i = 0; i <= arr.length - 1; i++) {
            int currentNum = arr[i];
            int leftSum = 0;
            int rightSum = 0;

            for (int left = 0; left < i; left++) {
                leftSum += arr[left];
            }
            for (int right = i + 1; right < arr.length; right++) {
                rightSum += arr[right];
            }

            if (leftSum == rightSum) {
                isEqual = true;
                System.out.println(i);
                break;
            }
        }

        if (!isEqual) {
            System.out.println("no");
        }
    }
}
