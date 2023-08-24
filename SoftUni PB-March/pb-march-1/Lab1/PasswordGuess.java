package Lab1;

import java.util.Scanner;

public class PasswordGuess {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String passGuess = scanner.nextLine();
        String password = "s3cr3t!P@ssw0rd";
        if (passGuess.equals(password)) {
            System.out.println("Welcome");
        } else {
            System.out.println("Wrong password!");
        }
    }
}
