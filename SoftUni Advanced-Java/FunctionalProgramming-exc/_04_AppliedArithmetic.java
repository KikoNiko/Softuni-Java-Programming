import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

public class _04_AppliedArithmetic {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Function<List<Integer>, List<Integer>> add = list -> list.stream()
                .map(e -> e + 1)
                .collect(Collectors.toList());

        Function<List<Integer>, List<Integer>> multiply = list -> list.stream()
                .map(e -> e * 2)
                .collect(Collectors.toList());

        Function<List<Integer>, List<Integer>> subtract = list -> list.stream()
                .map(e -> e - 1)
                .collect(Collectors.toList());

        Consumer<List<Integer>> print = list -> list.forEach(e -> System.out.print(e + " "));

        List<Integer> numbers = Arrays.stream(scanner.nextLine().split("\\s+"))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        String input = scanner.nextLine();
        while (!input.equals("end")) {
            switch (input) {
                case "add":
                    numbers = add.apply(numbers);
                    break;
                case "subtract":
                    numbers = subtract.apply(numbers);
                    break;
                case "multiply":
                    numbers = multiply.apply(numbers);
                    break;
                case "print":
                    print.accept(numbers);
                    System.out.println();
                    break;
                default:
                    System.out.println("not a valid operation");
            }

            input = scanner.nextLine();
        }

    }
}
