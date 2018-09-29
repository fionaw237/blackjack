import java.util.ArrayList;

public class Game {

    private ArrayList<Player> players;
    private Deck deck;
    private Player dealer;

    public Game(ArrayList<Player> players, Deck deck, Player dealer) {
        this.players = players;
        this.deck = deck;
        this.dealer = dealer;
        dealer.setAsDealer();
    }

    public void initialDeal() {

        for (int i = 0; i < numberOfPlayers() ; i++) {
            for (Player player : this.players){
                Card card = this.dealer.deal(this.deck);
                player.receiveCard(card);
                }
            }
    }

    public ArrayList<Player> getPlayers() {
        return new ArrayList<>(players);
    }

    public int numberOfPlayers() {
        return this.players.size();
    }


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

    public void changeDealer(Player player) {
        this.dealer.removeAsDealer();
        player.setAsDealer();
        this.dealer = player;
    }

    public Player getDealer() {
        return this.dealer;
    }

    public boolean checkInput(String choice){
        return (choice.equalsIgnoreCase("S")) || (choice.equalsIgnoreCase("T"));
    }

    public Deck getDeck() {
        return this.deck;
    }
}