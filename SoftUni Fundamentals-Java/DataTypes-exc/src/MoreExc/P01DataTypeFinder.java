package MoreExc;

import java.util.Scanner;

public class P01DataTypeFinder {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();
        String type = "";

        while (!input.equals("END")) {
            if (input.equalsIgnoreCase("true") || input.equalsIgnoreCase("false")) {
                type = "boolean";
            } else if (input.length() == 1) {
                char symbol = input.charAt(0);
                if (symbol < 47 || symbol > 57) {
                    type = "character";
                } else {
                    type = "integer";
                }
            } else {
                boolean isString = false;
                boolean isFloat = false;
                for (int i = 0; i < input.length(); i++) {
                    if (input.charAt(i) < 45 || input.charAt(i) > 57) {
                        isString = true;
                    }
                    if (input.charAt(i) == 46) {
                        isFloat = true;
                    }
                }
                if (isString) {
                    type = "string";
                } else if (isFloat) {
                    type = "floating point";
                } else {
                    type = "integer";
                }
            }

            System.out.printf("%s is %s type\n", input, type);
            input = scanner.nextLine();
        }

    }
}
