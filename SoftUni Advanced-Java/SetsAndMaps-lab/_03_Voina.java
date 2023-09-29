import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.stream.Collectors;

public class _03_Voina {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        LinkedHashSet<Integer> firstDeck = getCollect(scanner);
        LinkedHashSet<Integer> secondDeck = getCollect(scanner);

        int rounds = 50;

        while (rounds-- > 0) {

            int firstCard = firstDeck.iterator().next();
            firstDeck.remove(firstCard);
            int secondCard = secondDeck.iterator().next();
            secondDeck.remove(secondCard);

            if (firstCard > secondCard) {
                firstDeck.add(firstCard);
                firstDeck.add(secondCard);
            } else if (secondCard > firstCard) {
                secondDeck.add(firstCard);
                secondDeck.add(secondCard);
            }

            if (firstDeck.isEmpty() || secondDeck.isEmpty()) {
                break;
            }
        }

        String output;

        if (firstDeck.size() > secondDeck.size()) {
            output = "First player win!";
        } else if (secondDeck.size() > firstDeck.size()) {
            output = "Second player win!";
        } else {
            output = "Draw!";
        }
        System.out.println(output);
    }


    private static LinkedHashSet<Integer> getCollect(Scanner scanner) {
        return Arrays.stream(scanner.nextLine()
                        .split("\\s+"))
                .map(Integer::parseInt)
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }
}
