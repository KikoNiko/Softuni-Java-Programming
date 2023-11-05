package cards;

public class Card {

    private CardSuits suit;
    private CardRank rank;

    public Card(CardSuits suit, CardRank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public int getCardPower() {
        return this.suit.getValue() + this.rank.getValue();
    }

}
