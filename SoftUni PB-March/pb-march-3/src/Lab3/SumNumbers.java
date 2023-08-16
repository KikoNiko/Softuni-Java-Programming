package Lab3;

import java.util.Scanner;

public class SumNumbers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int numsCount = Integer.parseInt(scanner.nextLine());
        int result = 0;

        for (int i = 1; i <= numsCount; i++) {
            int nextNum = Integer.parseInt(scanner.nextLine());
            result += nextNum;
        }
        System.out.println(result);
    }
}
