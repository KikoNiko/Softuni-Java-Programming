import java.util.*;

public class Orders {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String data = scanner.nextLine();
        Map<String, Double> productPrice = new LinkedHashMap<>();
        Map<String, Integer> productQuantity = new HashMap<>();

        while (!data.equals("buy")) {
            String[] dataParts = data.split(" ");
            String product = dataParts[0];
            double price = Double.parseDouble(dataParts[1]);
            int quantity = Integer.parseInt(dataParts[2]);

            if (!productQuantity.containsKey(product)) {
                productQuantity.put(product, quantity);
            } else {
                productQuantity.put(product, productQuantity.get(product) + quantity);
            }
            productPrice.put(product, price);

            data = scanner.nextLine();
        }

        for (Map.Entry<String, Double> entry : productPrice.entrySet()) {
            String product = entry.getKey();
            double totalPrice = productQuantity.get(product) * entry.getValue();
            System.out.printf("%s -> %.2f%n", product, totalPrice);
        }

    }
}
