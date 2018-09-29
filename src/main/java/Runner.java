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
        Card firstCard = game.getDealer().firstCard();

        for (Player player : game.getPlayers()){
            if (!player.checkIfDealer()){
                System.out.println("Hi, " + player.getName() + "!");
//                for (Card card : player.getCards()){
//                    System.out.println(card.getName());
//                }
//                System.out.printf("which have a total value of " + player.getHandValue());
//                System.out.println("");
                System.out.println("The Dealer's first card is the " + firstCard.getName() );

                String choice = "T";

                while (choice.equalsIgnoreCase("T")){

                    System.out.println("Your cards are:");
                    for (Card playerCard : player.getCards()){
                        System.out.println(playerCard.getName());
                    }
                    System.out.printf("which have a total value of " + player.getHandValue());
                    System.out.println("");


                    System.out.println("Would you like to stick or twist? (Type S or T)");
                    Scanner scan = new Scanner(System.in);
                    choice = scan.next();

                    while (!game.checkInput(choice)){
                        System.out.println("Please type S to stick or T to twist");
                        choice = scan.next();
                    }

                    Card card = game.getDealer().deal(game.getDeck());
                    player.receiveCard(card);

                    if (player.getHandValue() > 21){
                        System.out.println("Bust - You lose!");
                        break;
                    }
                }
            }
        }
    }
}
