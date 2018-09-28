import java.util.ArrayList;

public class Dealer {

    private ArrayList<Card> cards;

    public Dealer() {
        this.cards = new ArrayList<>();
    }

    public Card deal(Deck deck){
        return deck.deal();
    }

    public int numberOfCards() {
        return this.cards.size();
    }

    public void receiveCard(Card card) {
        this.cards.add(card);
    }

    public int getHandValue() {
        int total = 0;
        for (Card card : this.cards){
            total += card.getValue();
        }
        return total;
    }
}
