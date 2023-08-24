package MoreExc;

import java.util.Scanner;

public class ResetPassword {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String rawPassword = scanner.nextLine();

        String input = scanner.nextLine();

        while (!input.equals("Done")) {
            if (input.contains("TakeOdd")) {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < rawPassword.length(); i++) {
                    if (i % 2 != 0) {
                        sb.append(rawPassword.charAt(i));
                    }
                }
                rawPassword = sb.toString();
                System.out.println(rawPassword);

            } else if (input.contains("Cut")) {
                int index = Integer.parseInt(input.split(" ")[1]);
                int length = Integer.parseInt(input.split(" ")[2]);
                String substringToCut = rawPassword.substring(index, index + length);
                rawPassword = rawPassword.replaceFirst(substringToCut, "");
                System.out.println(rawPassword);
            } else if (input.contains("Substitute")) {
                String substring = input.split(" ")[1];
                String substitute = input.split(" ")[2];
                if (rawPassword.contains(substring)) {
                    rawPassword = rawPassword.replaceAll(substring, substitute);
                    System.out.println(rawPassword);
                } else {
                    System.out.println("Nothing to replace!");
                }
            }

            input = scanner.nextLine();
        }

        System.out.println("Your password is: " + rawPassword);
    }
}
