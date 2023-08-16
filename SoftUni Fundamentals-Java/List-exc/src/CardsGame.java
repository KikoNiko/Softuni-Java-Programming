import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class CardsGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Integer> firstPlayerHand = Arrays.stream(scanner.nextLine()
                        .split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        List<Integer> secondPlayerHand = Arrays.stream(scanner.nextLine()
                        .split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());


        while (firstPlayerHand.size() != 0 && secondPlayerHand.size() != 0) {
            int firstPlayerCard = firstPlayerHand.get(0);
            int secondPlayerCard = secondPlayerHand.get(0);
            firstPlayerHand.remove(0);
            secondPlayerHand.remove(0);

            if (firstPlayerCard > secondPlayerCard) {
                firstPlayerHand.add(firstPlayerCard);
                firstPlayerHand.add(secondPlayerCard);
            } else if (secondPlayerCard > firstPlayerCard) {
                secondPlayerHand.add(secondPlayerCard);
                secondPlayerHand.add(firstPlayerCard);
            }
        }

        if (secondPlayerHand.size() == 0) {
            System.out.println("First player wins! Sum: " + getCardsSum(firstPlayerHand));
        } else {
            System.out.println("Second player wins! Sum: " + getCardsSum(secondPlayerHand));
        }

    }

    private static int getCardsSum(List<Integer> listCards) {
        int sum = 0;
        for (int card : listCards) {
            sum += card;
        }

        return sum;
    }
}
