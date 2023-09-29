import java.util.*;

public class _07_CitiesByContinentAndCountry {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());

        Map<String, LinkedHashMap<String, List<String>>> data = new LinkedHashMap<>();

        while (n-- > 0) {
            String input = scanner.nextLine();
            String[] tokens = input.split("\\s+");
            String continent = tokens[0];
            String country = tokens[1];
            String city = tokens[2];

            data.putIfAbsent(continent, new LinkedHashMap<>());
            data.get(continent).putIfAbsent(country, new ArrayList<>());
            data.get(continent).get(country).add(city);

        }

        data.forEach((key, value) -> {
            System.out.println(key + ":");

            value.forEach((k, v) -> {
                String cities = String.join(", ", v);
                System.out.println("  " + k + " -> " + cities);
            });
        });
    }
}
