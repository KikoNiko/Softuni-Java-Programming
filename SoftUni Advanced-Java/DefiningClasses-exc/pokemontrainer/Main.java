package pokemontrainer;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<String, List<Pokemon>> pokemonByTrainers = new LinkedHashMap<>();

        String data = scanner.nextLine();
        while (!data.equals("Tournament")) {

            String[] tokens = data.split("\\s+");
            String trainerName = tokens[0];
            String pokemonName = tokens[1];
            String element = tokens[2];
            int health = Integer.parseInt(tokens[3]);

            Pokemon pokemon = new Pokemon(pokemonName, element, health);

            pokemonByTrainers.putIfAbsent(trainerName, new ArrayList<>());
            pokemonByTrainers.get(trainerName).add(pokemon);

            data = scanner.nextLine();
        }

        List<Trainer> trainers = pokemonByTrainers.entrySet()
                .stream()
                .map(t -> new Trainer(t.getKey(), t.getValue()))
                .collect(Collectors.toList());

        String elementType = scanner.nextLine();
        while (!elementType.equals("End")) {
            for (Trainer trainer : trainers) {
                trainer.checkPokemon(elementType);
            }

            elementType = scanner.nextLine();
        }

        trainers.stream()
                .sorted(Comparator.comparing(Trainer::getNumberOfBadges).reversed())
                .forEach(System.out::println);
    }
}
