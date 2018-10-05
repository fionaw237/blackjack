import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PlayerTest {

    Player player;
    Player dealer;
    Deck deck;
    Card card1;

    @Before
    public void before(){
        player = new Player("Player 1");
        dealer = new Player("Player 2");
        dealer.setAsDealer();
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

    @Test
    public void canBeSetAsDealer(){
        assertEquals(false, player.checkIfDealer());
        player.setAsDealer();
        assertEquals(true, player.checkIfDealer());
    }

    @Test
    public void canBeRemovedAsDealer(){
        player.setAsDealer();
        assertEquals(true, player.checkIfDealer());
        player.removeAsDealer();
        assertEquals(false, player.checkIfDealer());
    }

    @Test
    public void showFirstCardIfDealer(){
        Card card1 = new Card(Suit.HEARTS, Rank.NINE);
        Card card2 = new Card(Suit.SPADES, Rank.JACK);
        dealer.receiveCard(card1);
        dealer.receiveCard(card2);
        assertEquals(card1, dealer.firstCard());
    }

    @Test
    public void hasAce(){
        assertEquals(false, player.hasCertainCard(Rank.ACE));
        player.receiveCard(card1);
        assertEquals(true, player.hasCertainCard(Rank.ACE));
    }

    @Test
    public void hasRoyalCard(){
        assertEquals(false, player.hasCertainCard(Rank.QUEEN));
        Card queen = new Card(Suit.SPADES, Rank.QUEEN);
        player.receiveCard(queen);
        assertEquals(true, player.hasCertainCard(Rank.QUEEN));
    }

    @Test
    public void hasBlackjackTrue(){
        Card queen = new Card(Suit.SPADES, Rank.QUEEN);
        player.receiveCard(queen);
        player.receiveCard(card1);
        assertEquals(true, player.hasBlackjack());
    }

    @Test
    public void hasBlackjackFalse(){
        assertEquals(false, player.hasBlackjack());
    }

    @Test
    public void canChooseAceHigh(){
        player.receiveCard(card1);
        assertEquals(1, player.getHandValue());
        player.chooseAceHigh();
        assertEquals(11, player.getHandValue());
    }

    @Test
    public void canGetNumberOfAces(){
        assertEquals(0, player.getNumberOfAces());
        player.receiveCard(card1);
        assertEquals(1, player.getNumberOfAces());
        player.receiveCard(card1);
        assertEquals(2, player.getNumberOfAces());
    }

    @Test
    public void has21True() {
        Card newCard = new Card(Suit.HEARTS, Rank.QUEEN);
        player.receiveCard(card1);
        player.receiveCard(newCard);
        player.receiveCard(newCard);
        assertEquals(true, player.has21());
    }

    @Test
    public void has21False() {
        Card newCard = new Card(Suit.HEARTS, Rank.QUEEN);
        player.receiveCard(card1);
        assertEquals(false, player.has21());
    }

}
