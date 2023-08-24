package MoreExc;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class MovingTarget {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Integer> targets = Arrays.stream(scanner.nextLine()
                .split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        String input = scanner.nextLine();
        while (!input.equals("End")) {
            String [] commandParts = input.split(" ");
            int index = Integer.parseInt(commandParts[1]);

            switch (commandParts[0]) {
                case "Shoot" :
                    if (index >= 0 && index < targets.size()) {
                        int power = Integer.parseInt(commandParts[2]);
                        int shotTarget = targets.get(index);
                        shotTarget -= power;
                        if (shotTarget <= 0) {
                            targets.remove(index);
                        } else {
                            targets.set(index, shotTarget);
                        }
                    }
                    break;

                case "Add" :
                    if (index >= 0 && index < targets.size()) {
                        int value = Integer.parseInt(commandParts[2]);
                        targets.add(index, value);
                    } else {
                        System.out.println("Invalid placement!");
                    }
                    break;

                case "Strike" :
                    int radius = Integer.parseInt(commandParts[2]);
                    if (index - radius  >= 0 && index + radius < targets.size()) {

                        for (int i = index - radius; i <= index + radius; i++) {
                            targets.set(i, 0);
                        }
                        targets.removeAll(Collections.singleton(0));

                    } else {
                        System.out.println("Strike missed!");
                    }
                    break;
            }
            input = scanner.nextLine();
        }
        for (int i = 0; i < targets.size() - 1; i++) {
            System.out.print(targets.get(i) + "|");
        }
        System.out.println(targets.get(targets.size() - 1));
    }
}
