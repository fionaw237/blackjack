import java.util.ArrayList;
import java.util.Scanner;

public class Game {

    private ArrayList<Player> players;
    private Deck deck;
    private Player dealer;
    private Scanner scan;

    public Game(ArrayList<Player> players, Deck deck, Player dealer) {
        this.players = players;
        this.deck = deck;
        this.dealer = dealer;
        dealer.setAsDealer();
        scan = new Scanner(System.in);
    }

    public void play() {

        initialDeal();

        for (Player player : getPlayers()){
            playerTurn(player);
        }

        if (!allPlayersBust()){
            dealerTurn();
            getAllResults();
        }
    }

    public void initialDeal() {
        dealTwoCardsToEachPlayer();
    }

    public void dealTwoCardsToEachPlayer() {
        for (int i = 0; i < 2 ; i++) {
            for (Player player : players){
                Card card = dealer.deal(deck);
                player.receiveCard(card);
            }
        }
    }

    public ArrayList<Player> getPlayers() {
        return new ArrayList<>(players);
    }

    public int numberOfPlayers() {
        return players.size();
    }


    public Player getWinner(Player player){

        Player winner = null;
        if (isDraw(player)){
            return winner;
        }

        if (bothHave21(player)){
            if (dealer.hasBlackjack()){
                winner = dealer;
            }
            else if (player.hasBlackjack()){
                winner = player;
            }
        }
        else if (playerScoreIsHigher(player)){
            winner = player;
        }
        else if (!playerScoreIsHigher(player)){
            winner = dealer;
        }
        return winner;
    }

    public boolean playerScoreIsHigher(Player player){
        return dealer.getHandValue() < player.getHandValue();
    }

    public boolean isDraw(Player player){

        if (bothHave21(player)){
            return bothHaveBlackjack(player);
        }

        return scoresAreEqual(player);
    }

    private boolean bothHave21(Player player) {
        return dealer.has21() && player.has21();
    }

    public boolean scoresAreEqual(Player player) {
        return dealer.getHandValue() == player.getHandValue();
    }


    public boolean bothHaveBlackjack(Player player) {
        return dealer.hasBlackjack() == player.hasBlackjack();
    }

    public void changeDealer(Player player) {
        dealer.removeAsDealer();
        player.setAsDealer();
        dealer = player;
    }

    public Player getDealer() {
        return dealer;
    }

    public boolean checkInput(String choice, ArrayList<String> allowedInputs){
        return allowedInputs.contains(choice.toLowerCase());
    }

    public Deck getDeck() {
        return deck;
    }

    public void showCards(Player player) {
        for (Card playerCard : player.getCards()){
            System.out.println(playerCard.getName());
        }

        if (player == dealer){
            if (dealer.hasBlackjack()){
                GameDisplay.dealerHasBlackjack();
            }
            else {
                GameDisplay.dealerRunningTotal(dealer);
            }
        }

        GameDisplay.addBlankLine();
    }

    public String finalResult(Player player) {
        if (getDealer().getHandValue() > 21){
            return "Dealer is bust! You win, " + player.getName() + " :)";
        }
        else{
            Player winner = getWinner(player);
            if (winner == player){
                return "You win, " + player.getName() + "! :)";
            }
            else if (winner == null){
                return player.getName() + ", it's a draw!";
            }
            else {
                return "You lose, " + player.getName() + "! :(";
            }
        }
    }

    public Card additionalDeal(Player player) {
        Card card = getDealer().deal(getDeck());
        player.receiveCard(card);
        return card;
    }

    public boolean allPlayersBust() {
        boolean allBust = true;
        for (Player player : getPlayers()){
            if (player != dealer){
                if (!player.checkIfBust()){
                    allBust = false;
                }
            }
        }
        return allBust;
    }


    public void getAllResults() {
        for (Player player : getPlayers()){
            if (!player.checkIfDealer() && !player.checkIfBust()){
                String result = finalResult(player);
                GameDisplay.result(result);
            }
        }
    }

    public void dealerTurn() {
        GameDisplay.dealerSecondCard();
        showCards(getDealer());

        if (getDealer().hasBlackjack()){
            getDealer().chooseAceHigh();
        }

        while (getDealer().getHandValue() < 16){
            additionalDeal(getDealer());
            GameDisplay.dealerGetsAnotherCard();
            showCards(getDealer());
        }
    }

    public void playerTurn(Player player) {
        if (!player.checkIfDealer()){
            GameDisplay.addBlankLine();
            GameDisplay.welcome(player);
            Card dealerFirstCard = getDealer().firstCard();
            GameDisplay.dealerFirstCard(dealerFirstCard);

            String choice = "T";

            while (choice.equalsIgnoreCase("T")){

                GameDisplay.yourCardsAre();
                showCards(player);

                if (player.numberOfCards() == 5){
                    chooseAcesHighOrLow(player);
                    GameDisplay.playerTotal(player);
                    break;
                }

                GameDisplay.stickOrTwist();
                choice = scan.next();

                ArrayList<String> allowedChoices = new ArrayList<>();
                allowedChoices.add("s");
                allowedChoices.add("t");

                while (!checkInput(choice, allowedChoices)){
                    GameDisplay.ensureSorT();
                    choice = scan.next();
                }

                if (choice.equalsIgnoreCase("S")){

                    chooseAcesHighOrLow(player);

                    if (player.hasBlackjack()){
                        GameDisplay.youHaveBlackjack();
                    }
                    else {
                        GameDisplay.playerTotal(player);
                    }
                    break;
                }

                Card nextCard = additionalDeal(player);

                if (player.checkIfBust()){
                    GameDisplay.nextCard(nextCard);
                    GameDisplay.youLose(player);
                    GameDisplay.addBlankLine();
                    break;
                }
            }
        }
    }

    public void chooseAcesHighOrLow(Player player) {
        if (player.hasCertainCard(Rank.ACE)){
            GameDisplay.numberOfAces(player.getNumberOfAces());

            for (int aceNumber = 1; aceNumber < player.getNumberOfAces() + 1; aceNumber++) {

                GameDisplay.highOrLow(aceNumber);

                String aceChoice = scan.next();

                ArrayList<String> allowedChoicesAces = new ArrayList<>();
                allowedChoicesAces.add("h");
                allowedChoicesAces.add("l");

                while (!checkInput(aceChoice, allowedChoicesAces)){
                    GameDisplay.ensureHorL();
                    aceChoice = scan.next();
                }

                if (aceChoice.equalsIgnoreCase("H")){
                    player.chooseAceHigh();
                }
            }
        }
    }

}