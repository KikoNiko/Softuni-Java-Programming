import java.util.Scanner;

public class LettersChangeNumbers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] input = scanner.nextLine().split("\\s+");

        double totalSum = 0;

        for (String str : input) {
            totalSum += calculateString(str);
        }
        System.out.printf("%.2f", totalSum);
    }

    private static double calculateString (String str) {
        double sum = 0;

        String strNumber = "";
        for (int i = 1; i < str.length() - 1; i++) {
            strNumber += str.charAt(i);
        }

        double number = Double.parseDouble(strNumber);

        char firstLetter = str.charAt(0);
        if (Character.isUpperCase(firstLetter)) {
            sum += number / (firstLetter - 64);
        } else {
            sum += number * (firstLetter - 96);
        }

        char lastLetter = str.charAt(str.length() - 1);
        if (Character.isUpperCase(lastLetter)) {
            sum = sum - (lastLetter - 64);
        } else {
            sum = sum + (lastLetter - 96);
        }

        return sum;
    }
}
