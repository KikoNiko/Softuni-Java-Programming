import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Train {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Integer> wagons = Arrays
                .stream(scanner.nextLine().split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        int maxCapacity = Integer.parseInt(scanner.nextLine());

        String input = scanner.nextLine();
        while (!input.equals("end")) {
            String command = input.split(" ")[0];
            if (command.equals("Add")) {
                int passengers = Integer.parseInt(input.split(" ")[1]);
                wagons.add(passengers);
            } else{
                int passengersToAdd = Integer.parseInt(command);
                for (int i = 0; i < wagons.size(); i++) {
                    int currentWagon = wagons.get(i);
                    if (currentWagon + passengersToAdd <= maxCapacity) {
                        wagons.set(i, currentWagon + passengersToAdd);
                        break;
                    }
                }
            }
            input = scanner.nextLine();
        }
        wagons.forEach(wag -> System.out.print(wag + " "));
    }
}
