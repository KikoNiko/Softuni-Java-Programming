import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ListManipulationBasics {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Integer> numList = Arrays
                .stream(scanner.nextLine().split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        String input = scanner.nextLine();

        while (!input.equals("end")) {
            String[] inputParts = input.split(" ");
            String command = inputParts[0];

            switch (command) {
                case "Add" :
                    numList.add(Integer.valueOf(inputParts[1]));
                    break;
                case "Remove" :
                    int num = Integer.parseInt(inputParts[1]);
                    numList.remove(Integer.valueOf(num));
                    break;
                case "RemoveAt" :
                    int indexToRemove = Integer.parseInt(inputParts[1]);
                    numList.remove(indexToRemove);
                    break;
                case "Insert" :
                    int numToInsert = Integer.parseInt(inputParts[1]);
                    int insertIndex = Integer.parseInt(inputParts[2]);
                    numList.add(insertIndex, numToInsert);
                    break;
            }
            input = scanner.nextLine();
        }
        System.out.println(numList.toString().replaceAll("[\\[\\],]",""));
    }
}
