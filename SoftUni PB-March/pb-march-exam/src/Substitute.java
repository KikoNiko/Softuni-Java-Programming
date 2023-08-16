import java.util.Scanner;

public class Substitute {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int k = Integer.parseInt(scanner.nextLine());
        int l = Integer.parseInt(scanner.nextLine());
        int m = Integer.parseInt(scanner.nextLine());
        int n = Integer.parseInt(scanner.nextLine());

        int subsCount = 0;

        for (int firstNumFirstDigit = k; firstNumFirstDigit <= 8; firstNumFirstDigit++) {

            for (int firstNumSecondDigit = 9; firstNumSecondDigit >= l; firstNumSecondDigit--) {

                for (int secondNumFirstDigit = m; secondNumFirstDigit <= 8; secondNumFirstDigit++) {

                    for (int secondNumSecondDigit = 9; secondNumSecondDigit >= n; secondNumSecondDigit--) {

                        boolean isValid = firstNumFirstDigit % 2 == 0 &&
                                secondNumFirstDigit % 2 == 0 &&
                                firstNumSecondDigit % 2 != 0 &&
                                secondNumSecondDigit % 2 != 0;

                        int firstNum = firstNumFirstDigit * 10 + firstNumSecondDigit;
                        int secondNum = secondNumFirstDigit * 10 + secondNumSecondDigit;

                        if (isValid && firstNum == secondNum) {
                            System.out.println("Cannot change the same player.");
                        } else if (isValid && firstNum != secondNum) {
                            System.out.printf("%d%d - %d%d\n", firstNumFirstDigit, firstNumSecondDigit, secondNumFirstDigit, secondNumSecondDigit);
                            subsCount++;
                        }
                        if (subsCount == 6) {
                            return;
                        }
                    }
                }
            }
        }

    }
}
