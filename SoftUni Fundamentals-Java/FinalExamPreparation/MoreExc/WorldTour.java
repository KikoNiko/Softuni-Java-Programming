package MoreExc;

import java.util.Scanner;

public class WorldTour {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String stops = scanner.nextLine();
        StringBuilder sb = new StringBuilder(stops);

        String command = scanner.nextLine();
        while (!command.equals("Travel")) {
            String[] tokens = command.split(":");
            String operation = tokens[0];
            switch (operation) {
                case "Add Stop":
                    int index = Integer.parseInt(tokens[1]);
                    String stop = tokens[2];
                    if (index <= sb.length()) {
                        sb.insert(index, stop);
                    }
                    System.out.println(sb.toString());
                    break;
                case "Remove Stop":
                    int startIndex = Integer.parseInt(tokens[1]);
                    int endIndex = Integer.parseInt(tokens[2]);
                    if ((startIndex >= 0 && startIndex < sb.length()) && (endIndex >= 0 && endIndex < sb.length())) {
                        sb.delete(startIndex, endIndex + 1);
                    }
                    System.out.println(sb.toString());
                    break;
                case "Switch":
                    String oldString = tokens[1];
                    String newString = tokens[2];
                    if (sb.indexOf(oldString) != -1 && !oldString.equals(newString)) {
                        int oldIndex = sb.indexOf(oldString);
                        sb.delete(oldIndex, oldString.length());
                        sb.insert(oldIndex, newString);
                    }
                    System.out.println(sb.toString());
                    break;
            }
            command = scanner.nextLine();
        }

        System.out.println("Ready for world tour! Planned stops: " + sb.toString());
    }
}
