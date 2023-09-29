import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class _06_ProductShop {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();

        Map<String, LinkedHashMap<String, Double>> productShop = new TreeMap<>();

        while (!"Revision".equals(input)) {
            String[] tokens = input.split(", ");
            String shop = tokens[0];
            String product = tokens[1];
            double price = Double.parseDouble(tokens[2]);

            productShop.putIfAbsent(shop, new LinkedHashMap<>());
            productShop.get(shop).put(product, price);

            input = scanner.nextLine();
        }

        for (var entry : productShop.entrySet()) {
            System.out.println(entry.getKey() + "->");
            for (var productData : entry.getValue().entrySet()) {
                System.out.printf("Product: %s, Price: %.1f%n", productData.getKey(), productData.getValue());
            }
        }
    }
}
