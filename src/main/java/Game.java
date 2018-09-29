import java.util.ArrayList;

public class Game {

    private ArrayList<Player> players;
    private Deck deck;

    public Game(ArrayList<Player> players, Deck deck) {
        this.players = players;
        this.deck = deck;
    }

    public void play() {
        Player dealer = findDealer();
        if (dealer != null){
            for (Player player : this.players){
                Card card = dealer.deal(this.deck);
                player.receiveCard(card);
            }
        }
    }



    // ------ these functions are for multiple players in the high/low card game

    public Player getWinner(){

        if (isDraw()){
            return null;
        }

        Player winner = this.players.get(0);
        for (Player player : players){
            if (player.getHandValue() > winner.getHandValue()){
                winner = player;
            }
        }
        return winner;
    }

    public boolean isDraw(){
        int playersWithHighScore = 0;
        int highScore = highestScore();
        for (Player player : players){
            if (player.getHandValue() == highScore){
                playersWithHighScore += 1;            }
        }
        return playersWithHighScore > 1;
    }

    public int highestScore(){
        int highScore = 0;
        for (Player player : this.players){
            if (player.getHandValue() > highScore){
                highScore = player.getHandValue();
            }
        }
        return highScore;
    }

    public Player findDealer() {
        for (Player player : this.players){
            if (player.checkIfDealer()){
                return player;
            }
        }
        return null;
    }
}