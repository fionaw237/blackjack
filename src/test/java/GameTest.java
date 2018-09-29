import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class GameTest {

    Player player;
    Player dealer;
    Deck deck;
    Game game;
    ArrayList<Player> players;

    @Before
    public void before(){
        player = new Player("Player");
        dealer = new Player("Dealer");
        dealer.setAsDealer();
        players = new ArrayList<>();
        players.add(player);
        players.add(dealer);
        deck = new Deck();
        game = game = new Game(players, deck);
    }

//    @Test
//    public void canPlay(){
//        game.play();
//        assertEquals(2, player.numberOfCards());
//        assertEquals(2, dealer.numberOfCards());
//        assertEquals(48, deck.numberOfCards());
//    }

    @Test
    public void canFindDealer(){
        assertEquals(dealer, game.findDealer());
    }

    @Test
    public void findDealerGivesNullIfNoDealer(){
        dealer.removeAsDealer();
        assertEquals(null, game.findDealer());
    }

}
