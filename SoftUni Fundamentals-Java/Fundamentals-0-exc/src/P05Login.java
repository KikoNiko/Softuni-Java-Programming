import java.util.Scanner;

public class P05Login {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String username = scanner.nextLine();
        boolean loggedIn = false;
        int attempts = 0;
        String password = "";

        for (int i = username.length() - 1; i >= 0 ; i--) {
            password += username.charAt(i);
        }

        while (attempts < 4) {
            String passTry = scanner.nextLine();
            attempts++;
            if (attempts == 4) {
                System.out.printf("User %s blocked!", username);
                break;
            }
            if (passTry.equals(password)) {
                loggedIn = true;
                break;
            } else {
                System.out.println("Incorrect password. Try again.");
            }
        }

        if (loggedIn) {
            System.out.printf("User %s logged in.", username);
        }
    }
}
