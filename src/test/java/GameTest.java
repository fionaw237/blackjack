import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class GameTest {

    Player player1;
    Player player2;
    Deck deck;
    Game game;
    ArrayList<Player> players;

    @Before
    public void before(){
        player1 = new Player("Player 1");
        player2 = new Player("Player 2");
        players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        deck = new Deck();
        game = game = new Game(players, deck, player2);
    }

    @Test
    public void hasDealer(){
        assertEquals(player2, game.getDealer());
    }

    @Test
    public void canGetNoOfPlayers(){
        assertEquals(2, game.numberOfPlayers());
    }

    @Test
    public void initialDeal(){
        game.initialDeal();
        assertEquals(2, player1.numberOfCards());
        assertEquals(2, player2.numberOfCards());
        assertEquals(48, deck.numberOfCards());
    }


    @Test
    public void canGetWinner(){
        player1.receiveCard(new Card(Suit.HEARTS, Rank.TWO));
        player1.receiveCard(new Card(Suit.HEARTS, Rank.NINE));
        player2.receiveCard(new Card(Suit.CLUBS, Rank.THREE));
        player2.receiveCard(new Card(Suit.CLUBS, Rank.JACK));
        assertEquals(player2, game.getWinner());
    }

        @Test
    public void canGetHighScore(){
        player1.receiveCard(new Card(Suit.HEARTS, Rank.TWO));
        player2.receiveCard(new Card(Suit.CLUBS, Rank.SIX));
        player1.receiveCard(new Card(Suit.HEARTS, Rank.KING));
        player2.receiveCard(new Card(Suit.DIAMONDS, Rank.THREE));
        assertEquals(12, game.highestScore());
    }

    @Test
    public void isDraw(){
        player1.receiveCard(new Card(Suit.HEARTS, Rank.THREE));
        player2.receiveCard(new Card(Suit.CLUBS, Rank.SIX));
        player1.receiveCard(new Card(Suit.HEARTS, Rank.SIX));
        player2.receiveCard(new Card(Suit.DIAMONDS, Rank.THREE));
        assertEquals(true, game.isDraw());
    }

    @Test
    public void isNotDraw(){
        player1.receiveCard(new Card(Suit.HEARTS, Rank.ACE));
        player2.receiveCard(new Card(Suit.CLUBS, Rank.SIX));
        player1.receiveCard(new Card(Suit.HEARTS, Rank.TWO));
        player2.receiveCard(new Card(Suit.DIAMONDS, Rank.TWO));
        assertEquals(false, game.isDraw());
    }

    @Test
    public void getWinnerReturnsNullIfDraw(){
        player1.receiveCard(new Card(Suit.HEARTS, Rank.ACE));
        player2.receiveCard(new Card(Suit.CLUBS, Rank.SIX));
        player1.receiveCard(new Card(Suit.HEARTS, Rank.SIX));
        player2.receiveCard(new Card(Suit.DIAMONDS, Rank.ACE));
        assertEquals(null, game.getWinner());
    }

    @Test
    public void canChangeDealer(){
        game.changeDealer(player1);
        assertEquals(true, player1.checkIfDealer());
        assertEquals(false, player2.checkIfDealer());
    }

}
