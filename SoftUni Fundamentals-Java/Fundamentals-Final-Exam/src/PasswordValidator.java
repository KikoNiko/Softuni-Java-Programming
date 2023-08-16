import java.util.Scanner;

public class PasswordValidator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String password = scanner.nextLine();

        String commands = scanner.nextLine();
        while (!commands.equals("Complete")) {
            String[] tokens = commands.split(" ");
            if (commands.contains("Make Upper")) {
                int index = Integer.parseInt(tokens[2]);
                StringBuilder sb = new StringBuilder(password);
                sb.setCharAt(index, Character.toUpperCase(password.charAt(index)));
                password = sb.toString();
                System.out.println(password);

            } else if (commands.contains("Make Lower")) {
                int index = Integer.parseInt(tokens[2]);
                StringBuilder sb = new StringBuilder(password);
                sb.setCharAt(index, Character.toLowerCase(password.charAt(index)));
                password = sb.toString();
                System.out.println(password);

            } else if (commands.contains("Insert")) {
                int index = Integer.parseInt(tokens[1]);
                char c = tokens[2].charAt(0);
                if (index < 0 || index >= password.length()) {
                    break;
                }
                StringBuilder sb = new StringBuilder(password);
                sb.insert(index, c);
                password = sb.toString();
                System.out.println(password);

            } else if (commands.contains("Replace")) {
                String c = tokens[1];
                if (!password.contains(c)) {
                    continue;
                }
                int value = Integer.parseInt(tokens[2]);
                int asciiValue = c.charAt(0);
                int newValue = asciiValue + value;
                String newChar = (char) newValue + "";
                for (char ch : password.toCharArray()) {
                    if (ch == c.charAt(0)) {
                        password = password.replace(ch, newChar.charAt(0));
                    }
                }
                System.out.println(password);

            } else if (commands.equals("Validation")) {
                if (password.length() < 8) {
                    System.out.println("Password must be at least 8 characters long!");
                }
                for (char c : password.toCharArray()) {
                    if (c != '_' && !Character.isLetterOrDigit(c)) {
                        System.out.println("Password must consist only of letters, digits and _!");
                    }
                }

                for (int i = 0; i < password.length(); i++) {
                    if (Character.isUpperCase(password.charAt(i))) {
                        break;
                    }
                    if (i == password.length() - 1) {
                        System.out.println("Password must consist at least one uppercase letter!");
                    }
                }

                for (int i = 0; i < password.length(); i++) {
                    if (Character.isLowerCase(password.charAt(i))) {
                        break;
                    }
                    if (i == password.length() - 1) {
                        System.out.println("Password must consist at least one lowercase letter!");
                    }
                }
                boolean hasDigit = false;
                for (char c : password.toCharArray()) {
                    if (Character.isDigit(c)) {
                        hasDigit = true;
                        break;
                    }
                }
                if (!hasDigit) {
                    System.out.println("Password must consist at least one digit!");
                }
            }

            commands = scanner.nextLine();
        }
    }
}
