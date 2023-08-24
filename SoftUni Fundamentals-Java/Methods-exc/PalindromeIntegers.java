import java.util.Arrays;
import java.util.Scanner;

public class PalindromeIntegers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();

        while (!input.equals("END")) {
            printIsPalindrome(input);
            input = scanner.nextLine();
        }
    }

    public static void printIsPalindrome(String num) {
        boolean isPalindrome = true;
        int [] numArr = Arrays.stream(num.split(""))
                .mapToInt(Integer::parseInt)
                .toArray();
        for (int i = 0; i < numArr.length / 2; i++) {
            if (numArr[i] != numArr[numArr.length - 1 - i]) {
                isPalindrome = false;
                break;
            }
        }
        System.out.println(isPalindrome);
    }
}
