import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class _08_CustomComparator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Comparator<Integer> customComparator = ((a, b) -> {
            if (a % 2 == 0 && b % 2 != 0) {
                return -1;
            } else if (a % 2 != 0 && b % 2 == 0) {
                return 1;
            } else {
                return a.compareTo(b);
            }
        });

        Arrays.stream(scanner.nextLine().split("\\s+"))
                .map(Integer::parseInt)
                .sorted(customComparator)
                .forEach(n -> System.out.print(n + " "));

    }
}
