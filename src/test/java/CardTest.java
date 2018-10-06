import static org.junit.Assert.*;
import org.junit.*;

public class CardTest {

    Card card;

    @Before
    public void before(){
        card = new Card(Suit.HEARTS, Rank.ACE);
    }

    @Test
    public void canGetSuit(){
        assertEquals(Suit.HEARTS,card.getSuit());
    }

    @Test
    public void canGetRank(){
        assertEquals(Rank.ACE, card.getRank());
    }

    @Test
    public void aceHasValueOf11(){
        assertEquals(11, card.getValue());
    }

    @Test
    public void canGetNameOfCard(){
        assertEquals("ACE of HEARTS", card.getName());
    }

//    @Test
//    public void canMakeAceHigh(){
//        assertEquals(1, card.getValue());
//        card.makeHigh();
//        assertEquals(11, card.getValue());
//    }

}