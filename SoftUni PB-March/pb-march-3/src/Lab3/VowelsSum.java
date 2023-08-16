package Lab3;

import java.util.Scanner;

public class VowelsSum {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String text = scanner.nextLine();
        int result = 0;

        for (int i = 0; i <= text.length() - 1; i++) {
            if (text.charAt(i) == 'a') {
                result++;
            } else if (text.charAt(i) == 'e') {
                result += 2;
            } else if (text.charAt(i) == 'i') {
                result += 3;
            } else if (text.charAt(i) == 'o') {
                result += 4;
            } else if (text.charAt(i) == 'u') {
                result += 5;
            }
        }
        System.out.println(result);
    }
}
