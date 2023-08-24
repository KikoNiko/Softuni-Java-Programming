package MoreExc;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Pirates {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();
        Map<String, Integer> populationMap = new LinkedHashMap<>();
        Map<String, Integer> goldMap = new LinkedHashMap<>();

        while (!input.equals("Sail")) {
            String[] tokens = input.split("\\|\\|");
            String city = tokens[0];
            int population = Integer.parseInt(tokens[1]);
            int gold = Integer.parseInt(tokens[2]);

            if (populationMap.containsKey(city)) {
                int populationUpdate = populationMap.get(city) + population;
                int goldUpdate = goldMap.get(city) + gold;
                populationMap.put(city, populationUpdate);
                goldMap.put(city, goldUpdate);
            } else {
                populationMap.put(city, population);
                goldMap.put(city, gold);
            }

            input = scanner.nextLine();
        }

        String events = scanner.nextLine();
        while (!events.equals("End")) {
            String[] tokens = events.split("=>");
            String action = tokens[0];
            String town = tokens[1];

            if (action.equals("Plunder")) {
                int people = Integer.parseInt(tokens[2]);
                int gold = Integer.parseInt(tokens[3]);
                System.out.printf("%s plundered! %d gold stolen, %d citizens killed.%n", town, gold, people);

                int newPopulation = populationMap.get(town) - people;
                int newGold = goldMap.get(town) - gold;
                populationMap.put(town, newPopulation);
                goldMap.put(town, newGold);

                if (populationMap.get(town) <= 0 || goldMap.get(town) <= 0) {
                    populationMap.remove(town);
                    goldMap.remove(town);
                    System.out.println(town + " has been wiped off the map!");
                }
            } else if (action.equals("Prosper")) {
                int gold = Integer.parseInt(tokens[2]);
                if (gold < 0) {
                    System.out.println("Gold added cannot be a negative number!");
                } else {
                    int updatedGold = goldMap.get(town) + gold;
                    goldMap.put(town, updatedGold);
                    System.out.printf("%d gold added to the city treasury. %s now has %d gold.%n", gold, town, updatedGold);
                }
            }

            events = scanner.nextLine();
        }

        if (!populationMap.isEmpty()) {
            System.out.printf("Ahoy, Captain! There are %d wealthy settlements to go to:%n", populationMap.size());
            for (Map.Entry<String, Integer> entry : populationMap.entrySet()) {
                System.out.printf("%s -> Population: %d citizens, Gold: %d kg%n", entry.getKey(), entry.getValue(), goldMap.get(entry.getKey()));
            }
        } else {
            System.out.println("Ahoy, Captain! All targets have been plundered and destroyed!");
        }
    }
}
