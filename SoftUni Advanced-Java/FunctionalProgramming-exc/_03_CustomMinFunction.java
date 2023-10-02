import java.util.Arrays;
import java.util.Scanner;
import java.util.function.Function;

public class _03_CustomMinFunction {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Function<Integer[], Integer> findMin = arr ->
                Arrays.stream(arr)
                        .min(Integer::compare)
                        .get();

        Integer[] numbers = Arrays.stream(scanner.nextLine().split("\\s+"))
                .map(Integer::parseInt)
                .toArray(Integer[]::new);

        System.out.println(findMin.apply(numbers));
    }
}
