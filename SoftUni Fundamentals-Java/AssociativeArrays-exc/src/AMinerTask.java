import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class AMinerTask {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String resource = scanner.nextLine();

        Map<String, Integer> map = new LinkedHashMap<>();

        while (!resource.equals("stop")) {
            int quantity = Integer.parseInt(scanner.nextLine());
            if (map.containsKey(resource)) {
                map.put(resource, map.get(resource) + quantity);
            } else {
                map.put(resource, quantity);
            }

            resource = scanner.nextLine();
        }

        map.forEach((key, value) -> System.out.println(key + " -> " + value));
    }
}
