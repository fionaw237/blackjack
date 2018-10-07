import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DealerTest {

    Dealer dealer;
    Card card1;

    @Before
    public void setUp() {
        dealer = new Dealer("Dealer");
        card1 = new Card(Suit.HEARTS, Rank.ACE);
    }

    @Test
    public void hasName() {
        assertEquals("Dealer", dealer.getName());
    }

    @Test
    public void doesNotHaveCard(){
        assertEquals(false, dealer.hasCard());
    }

    @Test
    public void canReceiveCard(){
        dealer.receiveCard(card1);
        assertEquals(true, dealer.hasCard());
        assertEquals(1, dealer.numberOfCards());

    }

    @Test
    public void canGetValueOfHand(){
        Card card2 = new Card(Suit.HEARTS, Rank.FOUR);
        dealer.receiveCard(card1);
        dealer.receiveCard(card2);
        assertEquals(15, dealer.getHandValue());
    }


    @Test
    public void showFirstCard(){
        Card card1 = new Card(Suit.HEARTS, Rank.NINE);
        Card card2 = new Card(Suit.SPADES, Rank.JACK);
        dealer.receiveCard(card1);
        dealer.receiveCard(card2);
        assertEquals(card1, dealer.firstCard());
    }

    @Test
    public void hasAce(){
        assertEquals(false, dealer.hasCertainCard(Rank.ACE));
        dealer.receiveCard(card1);
        assertEquals(true, dealer.hasCertainCard(Rank.ACE));
    }

    @Test
    public void hasRoyalCard(){
        assertEquals(false, dealer.hasCertainCard(Rank.QUEEN));
        Card queen = new Card(Suit.SPADES, Rank.QUEEN);
        dealer.receiveCard(queen);
        assertEquals(true, dealer.hasCertainCard(Rank.QUEEN));
    }

    @Test
    public void hasBlackjackTrue(){
        Card queen = new Card(Suit.SPADES, Rank.QUEEN);
        dealer.receiveCard(queen);
        dealer.receiveCard(card1);
        assertEquals(true, dealer.hasBlackjack());
    }

    @Test
    public void hasBlackjackFalse(){
        assertEquals(false, dealer.hasBlackjack());
    }


    @Test
    public void canGetNumberOfAces(){
        assertEquals(0, dealer.getNumberOfAces());
        dealer.receiveCard(card1);
        assertEquals(1, dealer.getNumberOfAces());
        dealer.receiveCard(card1);
        assertEquals(2, dealer.getNumberOfAces());
    }

    @Test
    public void has21True() {
        Card newCard = new Card(Suit.HEARTS, Rank.QUEEN);
        dealer.receiveCard(card1);
        dealer.receiveCard(newCard);
        assertEquals(true, dealer.has21());
    }

    @Test
    public void has21False() {
        dealer.receiveCard(card1);
        assertEquals(false, dealer.has21());
    }
}
