import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class _10_PredicateParty {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Predicate<String> predicate;

        List<String> names = Arrays.stream(scanner.nextLine().split("\\s")).collect(Collectors.toList());

        String input = scanner.nextLine();
        while (!input.equals("Party!")) {
            String criteria = input.split("\\s+")[2];
            if (input.contains("StartsWith")) {
                predicate = str -> str.startsWith(criteria);
            } else if (input.contains("EndsWith")) {
                predicate = str -> str.endsWith(criteria);
            } else {
                predicate = str -> str.length() == Integer.parseInt(criteria);
            }

            if (input.contains("Remove")) {
                names.removeIf(predicate);
            } else {
                List<String> toDouble = names.stream().filter(predicate).collect(Collectors.toList());
                names.addAll(toDouble);
            }
            input = scanner.nextLine();
        }

        if (names.isEmpty()) {
            System.out.println("Nobody is going to the party!");
        } else {
            String result = names.stream()
                    .sorted()
                    .collect(Collectors.joining(", ")) + " are going to the party!";
            System.out.println(result);
        }
    }
}
