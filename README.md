# Blackjack

- This is a basic text-based Blackjack game allowing 1 or more human players to play against a computer dealer. It is currently set up with two players and a dealer.

- Can run using IDE/terminal.

## Rules

1. Aces may be counted as 1 or 11 points, cards 2 to 9 are counted according to card value, and tens and face cards count as ten points (NOTE: currently only the human players can set ace as high - the computer will only do this if it has a "blackjack".)

2. The value of a hand is the sum of the point values of the individual cards

 - Except, a "blackjack" is the highest hand, consisting of an ace and any 10-point card, and it outranks all other 21-point hands

3. To start, the dealer will give two cards to each player and two cards to herself/himself

4. One of the dealer cards is dealt face up

5. Play begins with the player to the dealer's left. The following are the choices available to the player:

 - Stand: Player stands pat with his cards
- Twist: Player draws another card (and more if he wishes). If this card causes the player's total points to exceed 21 (known as "breaking" or "busting"), then she/he loses

6. After each player has had her/his turn, the dealer will turn over their hole card.
- If the dealer has 16 or less, they will draw another card
- If the dealer goes over 21 points, then any player who didn't already bust will win
- If the dealer does not bust, then the higher point total between the player and dealer will win
