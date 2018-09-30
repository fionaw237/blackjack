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


    public Player getWinner(Player winner){

        if (isDraw(winner)){
            return null;
        }

        if (this.dealer.getHandValue() > winner.getHandValue()){
            winner = dealer;
        }
        return winner;
    }

    public boolean isDraw(Player player){

        if (this.dealer.getHandValue() == 21 && player.getHandValue() == 21){
            System.out.println("hi");
            return dealer.hasBlackjack() == player.hasBlackjack();
        }

        return this.dealer.getHandValue() == player.getHandValue();
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

    public boolean checkAceChoice(String choice){
        return (choice.equalsIgnoreCase("H")) || (choice.equalsIgnoreCase("L"));
    }

    public Deck getDeck() {
        return this.deck;
    }

    public void showCards(Player player) {
        for (Card playerCard : player.getCards()){
            System.out.println(playerCard.getName());
        }

        if (player == this.dealer){
            if (this.dealer.hasBlackjack()){
                System.out.println("Dealer has blackjack!");
            }
            else {
                System.out.println("which have a total value of " + player.getHandValue());
            }
        }

        System.out.println("");
    }

    public String finalResult(Player player) {
        if (getDealer().getHandValue() > 21){
            return "Dealer is bust! You win! :)";
        }
        else{
            Player winner = getWinner(player);
            if (winner == player){
                return "You win! :)";
            }
            else if (winner == null){
                return "Draw!";
            }
            else {
                return "You lose! :(";
            }
        }
    }

    public Card additionalDeal(Player player) {
        Card card = getDealer().deal(getDeck());
        player.receiveCard(card);
        return card;
    }


//    public Player getWinner(){
//
//        if (isDraw()){
//            return null;
//        }
//
//        Player winner = this.players.get(0);
//        for (Player player : players){
//            if (player.getHandValue() > winner.getHandValue()){
//                winner = player;
//            }
//        }
//        return winner;
//    }

//    public boolean isDraw(){
//        int playersWithHighScore = 0;
//        int highScore = highestScore();
//        for (Player player : players){
//            if (player.getHandValue() == highScore){
//                playersWithHighScore += 1;            }
//        }
//        return playersWithHighScore > 1;
//    }
//
//    public int highestScore(){
//        int highScore = 0;
//        for (Player player : this.players){
//            if (player.getHandValue() > highScore){
//                highScore = player.getHandValue();
//            }
//        }
//        return highScore;
//    }

}