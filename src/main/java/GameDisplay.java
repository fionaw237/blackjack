public class GameDisplay {

    public static void addBlankLine() {
        System.out.println("");
    }

    public static void dealerHasBlackjack() {
        System.out.println("Dealer has blackjack!");
    }

    public static void nextCard(Card card) {
        System.out.println(card.getName());
    }

    public static void youLose(Player player) {
        System.out.println("Bust - You lose, " + player.getName() + "! :(");
    }

    public static void welcome(Player player) {
        System.out.println("Hi, " + player.getName() + ", it's your turn!");
    }

    public static void dealerFirstCard(Card dealerFirstCard) {
        System.out.println("The Dealer's first card is the " + dealerFirstCard.getName());
    }

    public static void stickOrTwist() {
        System.out.println("Would you like to stick or twist? (Type S or T)");
    }

    public static void ensureSorT() {
        System.out.println("Please type S to stick or T to twist");
    }

    public static void highOrLow(int aceNumber) {
        System.out.println("Ace number " + aceNumber + ": high or low? Type H or L");
    }

    public static void dealerGetsAnotherCard() {
        System.out.println("Dealer gets another card...now has");
    }

    public static void youHaveBlackjack() {
        System.out.println("You have blackjack!");
    }

    public static void ensureHorL() {
        System.out.println("Please type H for high or L for low");
    }

    public static void playerTotal(Player player) {
        System.out.println(player.getName() + ", your total is " + player.getHandValue());
    }

    public static void dealerSecondCard() {
        System.out.println("Time for the dealer to show their second card...cards are:");
    }

    public static void result(String result) {
        System.out.println(result);
    }

    public static void dealerRunningTotal(Player dealer) {
        System.out.println("which have a total value of " + dealer.getHandValue());
    }

    public static void numberOfAces(int number) {
        System.out.println("You have " + number + " aces(s)");
    }

    public static void yourCardsAre() {
        System.out.println("Your cards are:");
    }
}
