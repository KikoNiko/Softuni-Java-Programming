import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidPassword {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());
        String regex = "_\\.+[A-Z][A-Za-z0-9]{4,}[A-Z]_\\.+";

        for (int i = 0; i < n; i++) {
            String password = scanner.nextLine();
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(password);
            if (matcher.find()) {
                String validPassword = matcher.group();
                StringBuilder group = new StringBuilder();
                for (char c : validPassword.toCharArray()) {
                    if (Character.isDigit(c)) {
                        group.append(c);
                    }
                }
                //group.isEmpty()  but judge gives compilation error
                if (group.length() == 0) {
                    System.out.println("Group: default");
                } else {
                    System.out.println("Group: " + group.toString());
                }
            } else {
                System.out.println("Invalid pass!");
            }
        }
    }
}
