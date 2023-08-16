import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MessageDecrypter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());
        String regex = "^([$%])(?<tag>[A-Z][a-z]{2,})\\1: \\[(?<number1>[0-9]+)\\]\\|\\[(?<number2>[0-9]+)\\]\\|\\[(?<number3>[0-9]+)\\]\\|$";
        Pattern pattern = Pattern.compile(regex);

        for (int i = 0; i < n; i++) {
            String message = scanner.nextLine();

            boolean isValidMessage = false;
            Matcher matcher = pattern.matcher(message);
            if (matcher.find()) {
                isValidMessage = true;

            }
            if (isValidMessage) {
                String tag = matcher.group("tag");
                String decryptedMessage = "";
                int num1 = Integer.parseInt(matcher.group("number1"));
                int num2 = Integer.parseInt(matcher.group("number2"));
                int num3 = Integer.parseInt(matcher.group("number3"));
                StringBuilder decrypted = new StringBuilder();
                decrypted.append((char) num1);
                decrypted.append((char) num2);
                decrypted.append((char) num3);
                decryptedMessage = decrypted.toString();

                System.out.printf("%s: %s%n", tag, decryptedMessage);
            } else {
                System.out.println("Valid message not found!");
            }
        }
    }

}
