import java.util.ArrayList;
//import java.util.Random;
import java.util.*;


public class Deck {

    private ArrayList<Card> cards;

    public Deck() {
        this.cards = new ArrayList<>();
        populate();
        shuffle();
    }

    public ArrayList<Card> getCards() {
        return new ArrayList<Card>(cards);
    }

    private void populate() {
        for (Suit suit : Suit.values()){

            for (Rank rank : Rank.values()){
                Card card = new Card(suit, rank);
                cards.add(card);
            }
        }
    }

    private ArrayList<Card> shuffle() {
        Collections.shuffle(this.cards);
        return this.cards;
    }

    public boolean isShuffled() {
        Deck tempDeck = new Deck();
        tempDeck.populate();
        Card firstCard = tempDeck.getCards().get(0);
        Card secondCard = tempDeck.getCards().get(1);
        return (firstCard.getSuit() != this.cards.get(0).getSuit()) ||
                (firstCard.getValue() != this.cards.get(0).getValue());
    }

    public int numberOfCards(){
        return this.cards.size();
    }

    public Card deal() {
        Card nextCard = this.cards.get(0);
        this.cards.remove(0);
        return nextCard;
    }

}
