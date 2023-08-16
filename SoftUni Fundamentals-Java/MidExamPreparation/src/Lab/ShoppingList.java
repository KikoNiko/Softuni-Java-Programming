package Lab;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ShoppingList {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<String> shoppingList = Arrays
                .stream(scanner.nextLine()
                .split("!"))
                .collect(Collectors.toList());

        String input = scanner.nextLine();

        while (!input.equals("Go Shopping!")) {
            String [] commandParts = input.split(" ");
            String command = commandParts[0];
            String item = commandParts[1];

            switch (command) {
                case "Urgent" :
                    if (shoppingList.contains(item)) {
                        break;
                    } else {
                        shoppingList.add(0, item);
                    }
                    break;

                case "Unnecessary" :
                    shoppingList.remove(item);
                    break;

                case "Correct" :
                    String oldItem = commandParts[1];
                    if (shoppingList.contains(oldItem)) {
                        String newItem = commandParts[2];
                        int index = shoppingList.indexOf(oldItem);
                        shoppingList.set(index, newItem);
                    }
                    break;

                case "Rearrange" :
                    if (shoppingList.contains(item)) {
                        shoppingList.remove(item);
                        shoppingList.add(item);
                    }
                    break;
            }

            input = scanner.nextLine();
        }

        for (int i = 0; i < shoppingList.size() - 1; i++) {
            System.out.print(shoppingList.get(i) + ", ");
        }
        System.out.println(shoppingList.get(shoppingList.size() - 1));

        //System.out.println(String.join(", ", shoppingList));

    }
}
