import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class GameTest {

    Player player1;
    Player player2;
    Player player3;
    Player player4;
    Deck deck;
    Dealer dealer;
    Game game;
    ArrayList<Player> players;

    @Before
    public void before(){
        player1 = new Player("Player 1");
        player2 = new Player("Player 2");
        player3 = new Player("Player 3");
        player4 = new Player("Player 4");
        players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        players.add(player3);
        players.add(player4);
        deck = new Deck();
        dealer = new Dealer();

        game = new Game(players, dealer, deck);
    }

    @Test
    public void canPlay(){
        game.play();
//        game.getWinner();
        assertEquals(true, player1.hasCard());
        assertEquals(true, player2.hasCard());
        assertEquals(true, player3.hasCard());
        assertEquals(true, player4.hasCard());
        assertEquals(48, deck.numberOfCards());
    }

    @Test
    public void canGetWinner(){
        player1.receiveCard(new Card(Suit.HEARTS, Rank.ACE));
        player1.receiveCard(new Card(Suit.HEARTS, Rank.ACE));
        player2.receiveCard(new Card(Suit.CLUBS, Rank.TWO));
        player3.receiveCard(new Card(Suit.HEARTS, Rank.SIX));
        player4.receiveCard(new Card(Suit.DIAMONDS, Rank.THREE));
        Player winner = game.getWinner();
        assertEquals(player3, winner);
    }

    @Test
    public void canGetHighScore(){
        player1.receiveCard(new Card(Suit.HEARTS, Rank.ACE));
        player2.receiveCard(new Card(Suit.CLUBS, Rank.SIX));
        player3.receiveCard(new Card(Suit.HEARTS, Rank.KING));
        player4.receiveCard(new Card(Suit.DIAMONDS, Rank.THREE));
        assertEquals(13, game.highestScore());
    }

    @Test
    public void isDraw(){
        player1.receiveCard(new Card(Suit.HEARTS, Rank.ACE));
        player2.receiveCard(new Card(Suit.CLUBS, Rank.SIX));
        player3.receiveCard(new Card(Suit.HEARTS, Rank.SIX));
        player4.receiveCard(new Card(Suit.DIAMONDS, Rank.THREE));
        assertEquals(true, game.isDraw());
    }

    @Test
    public void isNotDraw(){
        player1.receiveCard(new Card(Suit.HEARTS, Rank.ACE));
        player2.receiveCard(new Card(Suit.CLUBS, Rank.SIX));
        player3.receiveCard(new Card(Suit.HEARTS, Rank.TWO));
        player4.receiveCard(new Card(Suit.DIAMONDS, Rank.TWO));
        assertEquals(false, game.isDraw());
    }

    @Test
    public void getWinnerReturnsNullIfDraw(){
        player1.receiveCard(new Card(Suit.HEARTS, Rank.ACE));
        player2.receiveCard(new Card(Suit.CLUBS, Rank.SIX));
        player3.receiveCard(new Card(Suit.HEARTS, Rank.SIX));
        player4.receiveCard(new Card(Suit.DIAMONDS, Rank.THREE));
        assertEquals(null, game.getWinner());
    }

    @Test
    public void getWinnerisNotNullIfWinner(){
        player1.receiveCard(new Card(Suit.HEARTS, Rank.ACE));
        player2.receiveCard(new Card(Suit.CLUBS, Rank.TWO));
        player3.receiveCard(new Card(Suit.HEARTS, Rank.SIX));
        player4.receiveCard(new Card(Suit.DIAMONDS, Rank.THREE));
        assertNotNull(game.getWinner());
    }

}
