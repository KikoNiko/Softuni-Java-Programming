import java.util.Scanner;

public class ValidUsernames {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] input = scanner.nextLine().split(", ");

        for (String s : input) {
            boolean isValidLength = s.length() > 3 && s.length() < 16;
            if (isValidLength) {
                int validChars = 0;
                for (char c : s.toCharArray()) {
                    if (Character.isLetterOrDigit(c) || c == '-' || c == '_') {
                        validChars++;
                    } else {
                        break;
                    }
                }
                if (validChars == s.length()) {
                    System.out.println(s);
                }
            }
        }
    }
}
