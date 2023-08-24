import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class LegendaryFarming {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<String, Integer> inventory = new LinkedHashMap<>();
        inventory.put("shards", 0);
        inventory.put("fragments", 0);
        inventory.put("motes", 0);

        Map<String, Integer> junk = new LinkedHashMap<>();

        boolean isWon = false;
        String legendaryItem = "";

        while (!isWon) {
            String input = scanner.nextLine().toLowerCase();
            String[] inputData = input.split(" ");

            for (int i = 0; i < inputData.length - 1; i += 2) {
                String material = inputData[i+1];
                int quantity = Integer.parseInt(inputData[i]);

                if (material.equals("shards") || material.equals("fragments") || material.equals("motes")) {
                    inventory.put(material, inventory.get(material) + quantity);
                } else {
                    if (junk.containsKey(material)) {
                        junk.put(material, junk.get(material) + quantity);
                    } else {
                        junk.put(material, quantity);
                    }
                }

                if (inventory.get("shards") >= 250) {
                    legendaryItem = "Shadowmourne";
                    inventory.put("shards", inventory.get("shards") - 250);
                    isWon = true;
                    break;
                } else if (inventory.get("fragments") >= 250) {
                    legendaryItem = "Valanyr";
                    inventory.put("fragments", inventory.get("fragments") - 250);
                    isWon = true;
                    break;
                } else if (inventory.get("motes") >= 250) {
                    legendaryItem = "Dragonwrath";
                    inventory.put("motes", inventory.get("motes") - 250);
                    isWon = true;
                    break;
                }
            }
        }

        System.out.println(legendaryItem + " obtained!");

        inventory.forEach((k, v) -> System.out.println(k + ": " + v));
        junk.forEach((k, v) -> System.out.println(k + ": " + v));
    }
}
