package shoppingSpree;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] peopleRaw = scanner.nextLine().split(";");
        Map<String, Person> peopleMap = new LinkedHashMap<>();
        for (String p : peopleRaw) {
            String[] personData = p.split("=");
            String name = personData[0];
            double money = Double.parseDouble(personData[1]);

            try {
                Person person = new Person(name, money);
                peopleMap.put(name, person);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        String[] productsRaw = scanner.nextLine().split(";");
        Map<String, Product> productMap = new LinkedHashMap<>();
        for (String pr : productsRaw) {
            String[] productData = pr.split("=");
            String name = productData[0];
            double cost = Double.parseDouble(productData[1]);

            try {
                Product product = new Product(name, cost);
                productMap.put(name, product);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        String command = scanner.nextLine();
        while (!"END".equals(command)) {
            String[] tokens = command.split(" ");
            String personName = tokens[0];
            String productName = tokens[1];

            Person buyer = peopleMap.get(personName);
            Product product = productMap.get(productName);
            try {
                buyer.buyProduct(product);
                System.out.printf("%s bought %s%n", personName, productName);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }

            command = scanner.nextLine();
        }

        peopleMap.values().forEach(System.out::println);
    }
}
