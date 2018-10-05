import java.util.ArrayList;

public class Runner {

    public static void main(String[] args){
        Player player1 = new Player("Player 1");
        Player player2 = new Player("Dealer");
        Player player3 = new Player("Player 2");
        ArrayList<Player> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        players.add(player3);
        Deck deck = new Deck();
        Game game = new Game(players, deck, player2);

        game.play();
    }
}
