import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PlayerTest {

    Player player;
    Dealer dealer;
    Deck deck;
    Card card1;

    @Before
    public void before(){
        player = new Player("Player 1");
        dealer = new Dealer();
        deck = new Deck();
        card1 = new Card(Suit.HEARTS, Rank.ACE);
    }

    @Test
    public void hasName(){
        assertEquals("Player 1", player.getName());
    }

    @Test
    public void doesNotHaveCard(){
        assertEquals(false, player.hasCard());
    }

    @Test
    public void canReceiveCard(){
        player.receiveCard(card1);
        assertEquals(true, player.hasCard());
        assertEquals(1, player.numberOfCards());

    }

    @Test
    public void canGetValueOfHand(){
        Card card2 = new Card(Suit.HEARTS, Rank.FOUR);
        player.receiveCard(card1);
        player.receiveCard(card2);
        assertEquals(5, player.getHandValue());
    }
}
