import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ChangeList {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Integer> numList = Arrays
                .stream(scanner.nextLine().split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        String input = scanner.nextLine();

        while (!input.equals("end")) {
            String [] command = input.split(" ");

            if (command[0].equals("Delete")) {
                int elementToDelete = Integer.parseInt(command[1]);
                numList.removeAll(Arrays.asList(elementToDelete));

            } else if (command[0].equals("Insert")) {
                int element = Integer.parseInt(command[1]);
                int position = Integer.parseInt(command[2]);
                numList.add(position, element);
            }

            input = scanner.nextLine();
        }
        System.out.println(numList.toString().replaceAll("[\\[\\],]", ""));
    }
}
