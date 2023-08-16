import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class CoffeeLover {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<String> coffeeList = Arrays
                        .stream(scanner.nextLine()
                        .split("\\s+"))
                        .collect(Collectors.toList());

        int n = Integer.parseInt(scanner.nextLine());

        for (int i = 1; i <= n; i++) {
            String command = scanner.nextLine();
            String[] commandParts = command.split(" ");
            switch (commandParts[0]) {
                case "Include" :
                    String coffeeToAdd = commandParts[1];
                    coffeeList.add(coffeeToAdd);
                    break;

                case "Remove" :
                    int numCoffeesToRemove = Integer.parseInt(commandParts[2]);
                    if (numCoffeesToRemove > coffeeList.size()){
                        break;
                    }
                    if (commandParts[1].equals("first")) {
                        for (int j = 0; j < numCoffeesToRemove; j++) {
                            coffeeList.remove(0);
                        }
                    } else if (commandParts[1].equals("last")) {
                        for (int j = 0; j < numCoffeesToRemove; j++) {
                            coffeeList.remove(coffeeList.size() - 1);
                        }
                    }
                    break;

                case "Prefer" :
                    int index1 = Integer.parseInt(commandParts[1]);
                    int index2 = Integer.parseInt(commandParts[2]);
                    if (index1 >= 0 && index1 < coffeeList.size() && index2 >= 0 && index2 < coffeeList.size()) {
                        Collections.swap(coffeeList, index1, index2);
                    }
                    break;

                case "Reverse" :
                    Collections.reverse(coffeeList);
                    break;
            }
        }
        System.out.println("Coffees:");
        for (String coffee : coffeeList) {
            System.out.print(coffee + " ");
        }
    }
}
