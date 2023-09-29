import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class _04_CountSymbols {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<Character, Integer> symbolMap = new TreeMap<>();

        String text = scanner.nextLine();

        for (int i = 0; i < text.length(); i++) {
            char symbol = text.charAt(i);

            if (!symbolMap.containsKey(symbol)) {
                symbolMap.put(symbol, 1);
            } else {
                symbolMap.put(symbol, symbolMap.get(symbol) + 1);
            }
        }

        symbolMap.forEach((k, v) -> System.out.printf("%s: %d time/s%n", k, v));
    }
}
