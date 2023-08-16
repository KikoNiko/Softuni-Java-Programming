import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExtractEmails {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String text = scanner.nextLine();
        List<String> validEmails = new ArrayList<>();

        String emailRegex = "\\b[A-Za-z0-9]+[.\\-_]*[A-Za-z0-9]+@[a-z]+\\-?[a-z]+(\\.[a-z]+\\-?[a-z]+)+\\b";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            System.out.println(matcher.group());
        }
    }
}
