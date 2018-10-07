import java.util.ArrayList;

public class Runner {

    public static void main(String[] args){
        Player player1 = new Player("Player 1");
        Player player2 = new Player("Player 2");
        Dealer dealer = new Dealer("Dealer");
        ArrayList<Player> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        Deck deck = new Deck();
        Game game = new Game(players, deck, dealer);

        game.play();
    }
}
