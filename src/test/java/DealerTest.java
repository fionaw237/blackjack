import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class DealerTest {

    Dealer dealer;
    Deck deck;

    @Before
    public void before(){
        dealer = new Dealer();
        deck = new Deck();
    }

    @Test
    public void canDealCard(){
        Card card = dealer.deal(deck);
        assertEquals(51, deck.numberOfCards());
        assertNotNull(card);
    }

    @Test
    public void startsWithNoCards(){
        assertEquals(0, dealer.numberOfCards());
    }

    @Test
    public void canReceiveCard(){
        Card card = new Card(Suit.HEARTS, Rank.ACE);
        dealer.receiveCard(card);
        assertEquals(1, dealer.numberOfCards());
    }

    @Test
    public void canGetValueOfHand(){
        Card card = new Card(Suit.HEARTS, Rank.FOUR);
        dealer.receiveCard(card);
        assertEquals(4, dealer.getHandValue());
    }

}
