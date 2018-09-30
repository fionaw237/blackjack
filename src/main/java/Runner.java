import java.util.ArrayList;
import java.util.Scanner;


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

        game.initialDeal();

        for (Player player : game.getPlayers()){
            if (!player.checkIfDealer()){
                System.out.println("");
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

                    while (!game.checkInput(choice)){
                        System.out.println("Please type S to stick or T to twist");
                        choice = scan.next();
                    }

                    if (choice.equalsIgnoreCase("S")){
                        if (player.hasCertainCard(Rank.ACE)){
                            System.out.println("You have "+ player.getNumberOfAces() + " aces(s)");
                            for (int i = 1; i < player.getNumberOfAces() + 1; i++) {
                                int aceNumber = i;
                                System.out.println("Ace number " + aceNumber + ": high or low? Type H or L");

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

                        if (player.hasBlackjack()){
                            System.out.println("You have blackjack!");
                        }
                        else {
                            System.out.println(player.getName() + ", your total is " + player.getHandValue());
                        }
                        break;
                    }

                    Card nextCard = game.additionalDeal(player);

                    if (player.checkIfBust()){
                        System.out.println(nextCard.getName());
                        System.out.println("Bust - You lose, " + player.getName() + "! :(");
                        System.out.println("");
                        break;
                    }
                }
            }
        }


        if (!game.allPlayersBust()){

            System.out.println("Time for the dealer to show their second card...cards are:");
            game.showCards(game.getDealer());

            if (game.getDealer().hasBlackjack()){
                System.out.println("Dealer has blackjack!");
            }

            while (game.getDealer().getHandValue() < 16){
                game.additionalDeal(game.getDealer());
                System.out.println("Dealer gets another card...now has");
                game.showCards(game.getDealer());
            }

            for (Player player : game.getPlayers()){
                if (!player.checkIfDealer() && !player.checkIfBust()){
                    String result = game.finalResult(player);
                    System.out.println(result);
                }
            }
        }
    }
}
