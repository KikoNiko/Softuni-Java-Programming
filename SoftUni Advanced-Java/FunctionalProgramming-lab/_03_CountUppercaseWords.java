import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class _03_CountUppercaseWords {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] words = scanner.nextLine().split("\\s+");

        Predicate<String> isUpperCase = s -> Character.isUpperCase(s.charAt(0));

        Function<String[], List<String>> getUpperCaseWords = arr -> Arrays.stream(arr)
                .filter(isUpperCase)
                .collect(Collectors.toList());

        List<String> upperCase = getUpperCaseWords.apply(words);

        System.out.println(upperCase.size());

        upperCase.forEach(System.out::println);
    }
}
