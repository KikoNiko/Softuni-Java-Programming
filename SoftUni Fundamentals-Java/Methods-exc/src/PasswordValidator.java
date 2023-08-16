import java.sql.SQLOutput;
import java.util.Scanner;

public class PasswordValidator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String password = scanner.nextLine();
        boolean isValidLength = checkPassLength(password);
        if (!isValidLength) {
            System.out.println("Password must be between 6 and 10 characters");
        }

        boolean isValidContent = checkLettersAndDigits(password);
        if (!isValidContent) {
            System.out.println("Password must consist only of letters and digits");
        }

        boolean isValidDigits = checkMinDigits(password);
        if (!isValidDigits) {
            System.out.println("Password must have at least 2 digits");
        }

        if (isValidLength && isValidContent && isValidDigits) {
            System.out.println("Password is valid");
        }
    }

    public static boolean checkPassLength(String pass) {

        return pass.length() >= 6 && pass.length() <= 10;
    }

    public static boolean checkLettersAndDigits(String pass) {
        for (char c : pass.toCharArray()) {
            if (!Character.isLetterOrDigit(c)) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkMinDigits(String pass) {
        int digitCount = 0;
        for (char c : pass.toCharArray()) {
            if (Character.isDigit(c)) {
                digitCount++;
            }
        }
        if (digitCount < 2) {
            return false;
        }
        return true;
    }
}
