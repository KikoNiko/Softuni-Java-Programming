import java.util.Arrays;
import java.util.Scanner;
import java.util.function.IntPredicate;
import java.util.stream.IntStream;

public class _06_FindEvenOrOdds {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] range = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        int start = range[0];
        int end = range[1];

        String condition = scanner.nextLine();

        if (condition.equals("odd")) {
            printRange(start, end, v -> v % 2 != 0);
        } else {
            printRange(start, end, v -> v % 2 == 0);
        }

    }

    private static void printRange(int start, int end, IntPredicate predicate) {
        IntStream.rangeClosed(start, end)
                .filter(predicate)
                .forEach(n -> System.out.print(n + " "));
        System.out.println();
    }
}
