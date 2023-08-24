import java.util.Arrays;
import java.util.Scanner;

public class MaxSequence {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int [] arr = Arrays.stream(scanner.nextLine()
                .split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int currentLength = 1;
        int maxLength = 1;
        int startIndex = 0;
        int firstStartIndex = 0;

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] == arr[i - 1]) {
                currentLength++;
            } else {
                currentLength = 1;
                startIndex = i;
            }

            if (currentLength > maxLength) {
                maxLength = currentLength;
                firstStartIndex = startIndex;
            }
        }

        for (int i = firstStartIndex; i < firstStartIndex + maxLength; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}
