package Lab;

import java.util.*;
import java.util.stream.Collectors;

public class PlantDiscovery {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int numOfPlants = Integer.parseInt(scanner.nextLine());

        Map<String, Integer> plantsRarity = new HashMap<>();
        Map<String, Double> plantsRating = new HashMap<>();

        for (int i = 0; i < numOfPlants; i++) {
            String plantInfo = scanner.nextLine();
            String plantName = plantInfo.split("<->")[0];
            int rarity = Integer.parseInt(plantInfo.split("<->")[1]);
            plantsRarity.put(plantName, rarity);
            plantsRating.put(plantName, 0.0);
        }

        String input = scanner.nextLine();
        while (!input.equals("Exhibition")) {
            String[] tokens = input.split("[: -]+");
            String command = tokens[0];
            String plant = tokens[1];
            switch (command) {
                case "Rate":
                    double rating = Double.parseDouble(tokens[2]);
                    if (plantsRating.get(plant) == 0) {
                        plantsRating.put(plant, rating);
                    } else {
                        double averageRating = (plantsRating.get(plant) + rating) / 2;
                        plantsRating.put(plant, averageRating);
                    }

                    break;
                case "Update":
                    int rarity = Integer.parseInt(tokens[2]);
                    plantsRarity.put(plant, rarity);
                    break;
                case "Reset":
                    plantsRating.put(plant, 0.0);
                    break;
                default :
                    System.out.println("error");
            }
            input = scanner.nextLine();
        }


        System.out.println("Plants for the exhibition:");
        for (Map.Entry<String, Integer> entry : plantsRarity.entrySet()) {
            System.out.printf("- %s; Rarity: %d; Rating: %.2f%n", entry.getKey(), entry.getValue(), plantsRating.get(entry.getKey()));
        }
    }
}
