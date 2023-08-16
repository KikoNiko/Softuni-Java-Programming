import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class AnonymousThreat {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<String> inputList = Arrays.stream(scanner.nextLine().split(" "))
                .collect(Collectors.toList());

        String command = scanner.nextLine();

        while (!command.equals("3:1")) {
            String[] commandParts = command.split(" ");

            if (commandParts[0].equals("merge")) {
                int startIndex = Integer.parseInt(commandParts[1]);
                int endIndex = Integer.parseInt(commandParts[2]);

                if (startIndex < 0) {
                    startIndex = 0;
                }
                if (endIndex > inputList.size() - 1) {
                    endIndex = inputList.size() - 1;
                }

                if (startIndex <= inputList.size() - 1 && endIndex >= 0 && endIndex <= inputList.size() - 1) {
                    String mergedText = "";
                    for (int i = startIndex; i <= endIndex; i++) {
                        String current = inputList.get(i);
                        mergedText += current;
                    }

                    for (int index = startIndex; index <= endIndex; index++) {
                        inputList.remove(startIndex);
                    }

                    inputList.add(startIndex, mergedText);
                }

            } else if (commandParts[0].equals("divide")) {
                int index = Integer.parseInt(commandParts[1]);
                int partitions = Integer.parseInt(commandParts[2]);

                if (index >= 0 && index < inputList.size()) {
                    String textToDivide = inputList.get(index);
                    inputList.remove(textToDivide);

                    int subParts = textToDivide.length() / partitions;
                    int beginIndex = 0;
                    for (int i = 1; i < partitions; i++) {
                        String textPart = textToDivide.substring(beginIndex, beginIndex + subParts);
                        inputList.add(index, textPart);
                        index++;
                        beginIndex += subParts;
                    }
                    String lastPart = textToDivide.substring(beginIndex, textToDivide.length());
                    inputList.add(index, lastPart);
                }

            }

            command = scanner.nextLine();
        }
        System.out.println(String.join(" ", inputList));
    }
}
