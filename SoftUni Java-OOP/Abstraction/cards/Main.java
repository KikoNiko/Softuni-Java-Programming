package cards;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
//        CardSuits[] cardSuits = CardSuits.values();
//
//        System.out.println("Card Suits:");
//        for (CardSuits suit : cardSuits) {
//            System.out.printf("Ordinal value: %d; Name value: %s%n", suit.ordinal(), suit.name());
//        }
//
//        CardRank[] cardRanks = CardRank.values();
//        System.out.println("Card Ranks:");
//        for (CardRank rank : cardRanks) {
//            System.out.printf("Ordinal value: %d; Name value: %s%n", rank.ordinal(), rank.name());
//        }

        Scanner scanner = new Scanner(System.in);

        String rank = scanner.nextLine();
        String suit = scanner.nextLine();

        Card card = new Card(CardSuits.valueOf(suit), CardRank.valueOf(rank));
        System.out.printf("Card name: %s of %s; Card power: %d", rank, suit, card.getCardPower());
    }
}
