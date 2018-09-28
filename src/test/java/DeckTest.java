import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DeckTest {

    Deck deck;

    @Before
    public void before(){
        deck = new Deck();
    }



    @Test
    public void canPopulateDeck(){
        assertEquals(52, deck.numberOfCards());
    }


    @Test
    public void canShuffleDeck(){
//        deck.shuffle();
        assertEquals(true, deck.isShuffled());
    }


    @Test
    public void canDealCard(){
//        deck.shuffle();
        Card nextCard = deck.getCards().get(0);
        Card receivedCard = deck.deal();
        assertEquals(nextCard, receivedCard);
    }



//    assertNotNull(deck.getFirstCard)


}
