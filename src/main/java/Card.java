public class Card {

    private Suit suit;
    private Rank rank;
    private boolean isHigh;

    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
        this.isHigh = false;
    }

    public Suit getSuit(){
        return this.suit;
    }

    public Rank getRank() {
        return rank;
    }

    public int getValue(){
        if (this.isHigh){
            return this.rank.getValue() + 10;
        }
        return this.rank.getValue();
    }

    public String getName() {
        return getRank().toString() + " of " + getSuit().toString();
    }

    public void makeHigh() {
        this.isHigh = true;
    }
}