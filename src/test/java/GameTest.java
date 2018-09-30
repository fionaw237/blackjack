import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class GameTest {

    Player player1;
    Player player2;
    Player player3;
    Deck deck;
    Game game;
    ArrayList<Player> players;

    @Before
    public void before(){
        player1 = new Player("Player 1");
        player2 = new Player("Player 2");
        player3 = new Player("Player 3");
        players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        players.add(player3);
        deck = new Deck();
        game = game = new Game(players, deck, player2);
    }

    @Test
    public void hasDealer(){
        assertEquals(player2, game.getDealer());
    }

    @Test
    public void canGetNoOfPlayers(){
        assertEquals(3, game.numberOfPlayers());
    }

    @Test
    public void initialDeal(){
        game.initialDeal();
        assertEquals(2, player1.numberOfCards());
        assertEquals(2, player2.numberOfCards());
        assertEquals(2, player3.numberOfCards());
        assertEquals(46, game.getDeck().numberOfCards());
    }

    @Test
    public void canGetWinner(){
        player1.receiveCard(new Card(Suit.HEARTS, Rank.TWO));
        player1.receiveCard(new Card(Suit.HEARTS, Rank.NINE));
        player2.receiveCard(new Card(Suit.CLUBS, Rank.THREE));
        player2.receiveCard(new Card(Suit.CLUBS, Rank.JACK));
        assertEquals(player2, game.getWinner(player1));
    }

    @Test
    public void isDraw(){
        player1.receiveCard(new Card(Suit.HEARTS, Rank.THREE));
        player2.receiveCard(new Card(Suit.CLUBS, Rank.SIX));
        player1.receiveCard(new Card(Suit.HEARTS, Rank.SIX));
        player2.receiveCard(new Card(Suit.DIAMONDS, Rank.THREE));
        assertEquals(true, game.isDraw(player1));
    }

    @Test
    public void isNotDraw(){
        player1.receiveCard(new Card(Suit.HEARTS, Rank.ACE));
        player2.receiveCard(new Card(Suit.CLUBS, Rank.SIX));
        player1.receiveCard(new Card(Suit.HEARTS, Rank.TWO));
        player2.receiveCard(new Card(Suit.DIAMONDS, Rank.TWO));
        assertEquals(false, game.isDraw(player1));
    }

    @Test
    public void getWinnerReturnsNullIfDraw(){
        player1.receiveCard(new Card(Suit.HEARTS, Rank.ACE));
        player2.receiveCard(new Card(Suit.CLUBS, Rank.SIX));
        player1.receiveCard(new Card(Suit.HEARTS, Rank.SIX));
        player2.receiveCard(new Card(Suit.DIAMONDS, Rank.ACE));
        assertEquals(null, game.getWinner(player1));
    }

    @Test
    public void canChangeDealer(){
        game.changeDealer(player1);
        assertEquals(true, player1.checkIfDealer());
        assertEquals(false, player2.checkIfDealer());
    }

    @Test
    public void checkUserInputTrue(){
        String choice = "S";
        assertEquals(true, game.checkInput(choice));
    }

    @Test
    public void checkUserInputFalse(){
        String choice = "batman";
        assertEquals(false, game.checkInput(choice));
    }

    @Test
    public void bothScore21DealerWinsWithBlackjack(){
        player1.receiveCard(new Card(Suit.HEARTS, Rank.ACE));
        player2.receiveCard(new Card(Suit.CLUBS, Rank.ACE));
        player1.receiveCard(new Card(Suit.HEARTS, Rank.TEN));
        player2.receiveCard(new Card(Suit.DIAMONDS, Rank.KING));
        player1.chooseAceHigh();
        player2.chooseAceHigh();
        assertEquals(21, game.getDealer().getHandValue());
        assertEquals(21, player1.getHandValue());
        assertEquals(false, game.isDraw(player1));

        assertEquals(game.getDealer(), game.getWinner(player1));
    }

    @Test
    public void bothScore21PlayerWinsWithBlackjack(){
        player1.receiveCard(new Card(Suit.HEARTS, Rank.ACE));
        player2.receiveCard(new Card(Suit.CLUBS, Rank.ACE));
        player2.receiveCard(new Card(Suit.HEARTS, Rank.TEN));
        player1.receiveCard(new Card(Suit.DIAMONDS, Rank.KING));
        player1.chooseAceHigh();
        player2.chooseAceHigh();
        assertEquals(21, game.getDealer().getHandValue());
        assertEquals(21, player1.getHandValue());
        assertEquals(false, game.isDraw(player1));
        assertEquals(player1, game.getWinner(player1));
    }

    @Test
    public void bothHaveBlackjackDraw(){
        player1.receiveCard(new Card(Suit.HEARTS, Rank.ACE));
        player2.receiveCard(new Card(Suit.CLUBS, Rank.ACE));
        player2.receiveCard(new Card(Suit.HEARTS, Rank.QUEEN));
        player1.receiveCard(new Card(Suit.DIAMONDS, Rank.KING));
        player1.chooseAceHigh();
        player2.chooseAceHigh();
        assertEquals(21, game.getDealer().getHandValue());
        assertEquals(21, player1.getHandValue());
        assertEquals(true, game.isDraw(player1));
    }

}
