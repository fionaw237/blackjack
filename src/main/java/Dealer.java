import java.util.ArrayList;

public class Dealer extends Player{

    public Dealer(String name) {
        super(name);
    }

    public Card deal(Player player, Deck deck){
        Card card = deck.deal();
        player.receiveCard(card);
        return card;
    }
}
