package MoreExc;

import java.util.Scanner;

public class SecretChat {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String secretMessage = scanner.nextLine();

        String instructions = scanner.nextLine();
        while (!instructions.equals("Reveal")) {
            String[] tokens = instructions.split(":\\|:");
            String command = tokens[0];
            switch (command) {
                case "InsertSpace":
                    int index = Integer.parseInt(tokens[1]);
                    StringBuilder sb = new StringBuilder(secretMessage);
                    sb.insert(index, " ");
                    secretMessage = sb.toString();
                    System.out.println(secretMessage);
                    break;
                case "Reverse":
                    String substring = tokens[1];
                    if (secretMessage.contains(substring)) {
                        int firstIndex = secretMessage.indexOf(substring);
                        StringBuilder smb = new StringBuilder(secretMessage);
                        smb.delete(firstIndex, firstIndex + substring.length());
                        secretMessage = smb.toString();
                        StringBuilder reversedSub = new StringBuilder(substring).reverse();
                        secretMessage = secretMessage + reversedSub;
                        System.out.println(secretMessage);
                    } else {
                        System.out.println("error");
                    }
                    break;
                case "ChangeAll":
                    String subString = tokens[1];
                    String replacement = tokens[2];
                    secretMessage = secretMessage.replaceAll(subString, replacement);
                    System.out.println(secretMessage);
                    break;
            }
            instructions = scanner.nextLine();
        }
        System.out.println("You have a new text message: " + secretMessage);
    }
}
