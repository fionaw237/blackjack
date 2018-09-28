import java.util.ArrayList;

public class Player {
    private String name;
    private ArrayList<Card> cards;

    public Player(String name) {
        this.name = name;
        this.cards = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public ArrayList<Card> getCards() {
        return new ArrayList<Card>(this.cards);
    }

    public int numberOfCards(){
        return this.cards.size();
    }

    public void receiveCard(Card card) {
        this.cards.add(card);
    }

    public boolean hasCard() {
        return this.cards.size() != 0;
    }

    public int getHandValue() {
        int total = 0;
        for (Card card : this.cards){
            total += card.getValue();
        }
        return total;
    }
}
