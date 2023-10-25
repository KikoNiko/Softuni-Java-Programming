package oct2321;

import java.util.*;

public class AutumnCocktails {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Deque<Integer> ingredientsQueue = new ArrayDeque<>();
        Deque<Integer> freshnessStack = new ArrayDeque<>();

        Arrays.stream(scanner.nextLine().split(" "))
                .map(Integer::parseInt)
                .forEach(ingredientsQueue::offer);
        Arrays.stream(scanner.nextLine().split(" "))
                .map(Integer::parseInt)
                .forEach(freshnessStack::push);

        Map<String, Integer> cocktailsMap = new TreeMap<>();


        while (!ingredientsQueue.isEmpty() && !freshnessStack.isEmpty()) {
            int ingredientValue = ingredientsQueue.peek();
            if (ingredientValue == 0) {
                ingredientsQueue.poll();
                continue;
            }
            int level = ingredientValue * freshnessStack.pop();

            if (isMatch(level)) {
                String cocktail = "";
                if (level == 150) {
                    cocktail = "Pear Sour";
                } else if (level == 250) {
                    cocktail = "The Harvest";
                } else if (level == 300) {
                    cocktail = "Apple Hinny";
                } else {
                    cocktail = "High Fashion";
                }
                createCocktail(cocktailsMap, cocktail);
                ingredientsQueue.poll();
            } else {
                ingredientsQueue.offer(ingredientsQueue.poll() + 5);
            }
        }

        if (cocktailsMap.size() == 4) {
            System.out.println("It's party time! The cocktails are ready!");
        } else {
            System.out.println("What a pity! You didn't manage to prepare all cocktails.");
        }

        if (!ingredientsQueue.isEmpty()) {
            int ingredientsSum = 0;
            for (Integer i : ingredientsQueue) {
                ingredientsSum += i;
            }
            System.out.println("Ingredients left: " + ingredientsSum);
        }

        for (Map.Entry<String, Integer> entry : cocktailsMap.entrySet()) {
            System.out.printf("# %s --> %d%n", entry.getKey(), entry.getValue());
        }

    }


    private static void createCocktail(Map<String, Integer> cocktailsMap, String cocktail) {
        if (cocktailsMap.containsKey(cocktail)) {
            cocktailsMap.put(cocktail, cocktailsMap.get(cocktail) + 1);
        } else {
            cocktailsMap.put(cocktail, 1);
        }
    }

    private static boolean isMatch(int result) {
        List<Integer> levels = List.of(150, 250, 300, 400);
        return levels.contains(result);
    }

}
