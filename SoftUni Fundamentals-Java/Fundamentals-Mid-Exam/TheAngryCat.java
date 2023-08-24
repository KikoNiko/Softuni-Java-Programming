import java.util.Arrays;
import java.util.Scanner;

public class TheAngryCat {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int [] priceRatings = Arrays.stream(scanner.nextLine()
                .split(", "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int entryPoint = Integer.parseInt(scanner.nextLine());

        String type = scanner.nextLine();

        if (type.equals("cheap")) {
            findSumCheap(priceRatings, entryPoint);
        } else if (type.equals("expensive")) {
            findSumExpensive(priceRatings, entryPoint);
        }

    }

    public static void findSumCheap (int[] arr, int index) {
        int sumLeft = 0;
        for (int i = 0; i < index; i++) {
            if (arr[i] < arr[index]) {
                sumLeft += arr[i];
            }
        }
        int sumRight = 0;
        for (int i = index + 1; i < arr.length; i++) {
            if (arr[i] < arr[index]) {
                sumRight += arr[i];
            }
        }
        if (sumLeft >= sumRight) {
            System.out.println("Left - " + sumLeft);
        } else {
            System.out.println("Right - " + sumRight);
        }
    }

    public static void findSumExpensive (int[] arr, int index) {
        int sumLeft = 0;
        for (int i = 0; i < index; i++) {
            if (arr[i] >= arr[index]) {
                sumLeft += arr[i];
            }
        }
        int sumRight = 0;
        for (int i = index + 1; i < arr.length; i++) {
            if (arr[i] >= arr[index]) {
                sumRight += arr[i];
            }
        }
        if (sumLeft >= sumRight) {
            System.out.println("Left - " + sumLeft);
        } else {
            System.out.println("Right - " + sumRight);
        }
    }
}
