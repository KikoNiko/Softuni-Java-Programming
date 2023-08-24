package Exc;

import java.util.Scanner;

public class TheImitationGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String message = scanner.nextLine();
        StringBuilder sb = new StringBuilder(message);

        String instructions = scanner.nextLine();
        while (!instructions.equals("Decode")) {
            String[] tokens = instructions.split("\\|");
            String command = tokens[0];
            switch (command) {
                case "Move":
                    int lettersToMove = Integer.parseInt(tokens[1]);
                    String substring = sb.substring(0, lettersToMove);
                    sb.delete(0, lettersToMove);
                    sb.append(substring);
                    break;
                case "Insert":
                    int index = Integer.parseInt(tokens[1]);
                    String value = tokens[2];
                    sb.insert(index, value);
                    break;
                case "ChangeAll":
                    String substringToChange = tokens[1];
                    String replacement = tokens[2];
                    String newMessage = sb.toString();
                    newMessage = newMessage.replace(substringToChange, replacement);
                    sb = new StringBuilder(newMessage);
                    break;
            }

            instructions = scanner.nextLine();
        }

        System.out.println("The decrypted message is: " + sb.toString());
    }
}
