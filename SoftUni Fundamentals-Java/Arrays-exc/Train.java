import java.util.Arrays;
import java.util.Scanner;

public class Train {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());
        int sum = 0;
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            int nextNum = Integer.parseInt(scanner.nextLine());
            arr[i] = nextNum;
            sum += nextNum;
        }
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
        System.out.println(sum);
    }
}
