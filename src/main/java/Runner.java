import java.util.ArrayList;
import java.util.Scanner;


public class Runner {

    public static void main(String[] args){
        Player player1 = new Player("Merlin");
        Player player2 = new Player("Player 2");
        ArrayList<Player> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        Deck deck = new Deck();
        Game game = new Game(players, deck, player2);

        game.initialDeal();

        for (Player player : game.getPlayers()){
            if (!player.checkIfDealer()){
                System.out.println("Hi, " + player.getName() + ", it's your turn!");
                Card dealerFirstCard = game.getDealer().firstCard();
                System.out.println("The Dealer's first card is the " + dealerFirstCard.getName());

                String choice = "T";

                while (choice.equalsIgnoreCase("T")){

                    System.out.println("Your cards are:");
                    game.showCards(player);

                    System.out.println("Would you like to stick or twist? (Type S or T)");
                    Scanner scan = new Scanner(System.in);
                    choice = scan.next();
                    if (choice.equalsIgnoreCase("S")){
                        if (player.hasCertainCard(Rank.ACE)){
                            int aces = player.numberOfAces();
                            System.out.println("You have "+ aces + " aces(s)");
                            for (int i = 0; i < player.numberOfAces(); i++) {
                                System.out.println("Ace number " + i+1 + ": high or low? Type H or L");

                                String aceChoice = scan.next();

                                while (!game.checkAceChoice(aceChoice)){
                                    System.out.println("Please type H for high or L for low");
                                    aceChoice = scan.next();
                                }

                                if (aceChoice.equalsIgnoreCase("H")){
                                    player.chooseAceHigh();
                                }
                            }
                        }
                        System.out.println("Your total is " + player.getHandValue());
                        break;
                    }

                    while (!game.checkInput(choice)){
                        System.out.println("Please type S to stick or T to twist");
                        choice = scan.next();
                    }

                    Card card = game.getDealer().deal(game.getDeck());
                    player.receiveCard(card);

                    if (player.getHandValue() > 21){
                        System.out.println("Bust - You lose! :(");
                        player.setIsBust();
                        break;
                    }
                }

                if (!player.checkIfBust()){

                    System.out.println("Time for the dealer to show their second card...cards are:");
                    game.showCards(game.getDealer());

                    while (game.getDealer().getHandValue() < 16){
                        Card card = game.getDealer().deal(game.getDeck());
                        game.getDealer().receiveCard(card);
                        System.out.println("Dealer gets another card...now has");
                        game.showCards(game.getDealer());
                    }

                    if (game.getDealer().getHandValue() > 21){
                        System.out.println("Dealer is bust! You win! :)");
                    }
                    else{
                        Player winner = game.getWinner(player);
                        if (winner == player){
                            System.out.println("You win! :)");
                        }
                        else if (winner == null){
                            System.out.println("Draw!");
                        }
                        else if (winner == game.getDealer()) {
                            System.out.println("You lose! :(");
                        }
                    }
                }
            }
        }
    }
}
