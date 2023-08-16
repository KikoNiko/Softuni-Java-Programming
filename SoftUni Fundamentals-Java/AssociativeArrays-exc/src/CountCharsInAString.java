import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class CountCharsInAString {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine().replaceAll(" ", "");

        Map<Character, Integer> countChars = new LinkedHashMap<>();

        for (char c : input.toCharArray()) {
            if (countChars.containsKey(c)) {
                countChars.put(c, countChars.get(c) + 1);
            } else {
                countChars.put(c, 1);
            }
        }

        countChars.forEach((key, value) -> System.out.println(key + " -> " + value));
    }
}
