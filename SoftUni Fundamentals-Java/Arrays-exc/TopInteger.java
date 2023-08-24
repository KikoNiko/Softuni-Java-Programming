import java.util.Arrays;
import java.util.Scanner;

public class TopInteger {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int [] arr = Arrays.stream(scanner.nextLine()
                .split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        for (int i = 0; i < arr.length - 1; i++) {
            boolean isGreater = true;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] <= arr[j]) {
                    isGreater = false;
                    break;
                }
            }
            if (isGreater) {
                System.out.print(arr[i] + " ");
            }
        }
        System.out.print(arr[arr.length - 1]);
    }
}
