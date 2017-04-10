import java.util.Scanner;

/*
 * This program simulates the game of BlackJack. The computer acts as the
 * dealer. The user has a beginning amount of $100, and makes a bet on each 
 * game. The user can leave at any time (by entering 0), or will be kicked 
 * out when they lose all their money. The dealer automatically hits on a 
 * total of 16 or less and stands on a total of 17 or more. The dealer wins 
 * ties. A new deck of cards is used for each game.
 */

public class BlackJackDriver {
	private static Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args) {
		run();
	}
	
	public static void run() {
		int money = 100; // Starting amount of user's money
		int bet; // Amount user bets on for a single game
		boolean userWins; 

		System.out.println("You are about to play BlackJack! \n\n");

		while (true) {
			System.out.println("You have " + money + " dollar(s)");
			do {
				System.out.println("How much money do you want to bet?  (Enter 0 to end)");
				System.out.print("$");
				bet = scanner.nextInt();
				if (bet < 0 || bet > money) {
					System.out.println("Your answer must be between 0 and " + money);
				}
			} while (bet < 0 || bet > money);
			if (bet == 0) {
				break;
			}
			
			userWins = playBlackjack();
			if (userWins) { 
				money += bet;
			} else {
				money -= bet;
			}
			System.out.println();
			
			if (money == 0) {
				System.out.println("You don't have anymore money to play!");
				break;
			}
		}
		System.out.println("\n You leave with $" + money);
	}
	
	// Return true if the user wins, false if the user loses
	private static boolean playBlackjack() {

		// Create a shuffled deck of cards
		Deck deck = new Deck();
		deck.populate();
		deck.shuffle();
		
		// Create two hands-- one for the user and the other for the dealer
		Hand userHand = new Hand();
		Hand dealerHand = new Hand();

		// Deal two cards to each player
		deck.deal(userHand, 2);
		deck.deal(dealerHand, 2);
		
		System.out.println();


		// Check if one of the players has Blackjack (two cards totaling to 21).
		// The player with Blackjack wins the game. Dealer wins ties.
		if(dealerHand.getTotal() == 21) {
			showHands(userHand, dealerHand);
			
			System.out.println("Dealer has Blackjack. Dealer wins.");
			return false;
		}


		if (userHand.getTotal() == 21) {
			showHands(userHand, dealerHand);
			
			System.out.println("You have Blackjack. You win.");
			return true;
		}


		// If neither player has Blackjack, play the game. The user gets a
		// choice to draw cards (hit) or not (stand). The loop ends when the
		// user chooses to stand or when the user goes over 21.
		while (true) {

			// Display user's cards, and let user decide to (H)it or (S)tand 
			System.out.println("Your cards are: ");
			System.out.println(userHand.showHand());
			
			System.out.println("Dealer is showing the ");
			System.out.println(dealerHand.cards.get(0));
			System.out.println();
			System.out.print("Hit (H) or Stand (S)?");
			
			char userAction; // User's response, 'H' or 'S'
			do {
				userAction = Character.toUpperCase(scanner.next().charAt(0));
				if (userAction != 'H' && userAction != 'S') {
					System.out.print("Please choose either H or S: ");
				}
			} while (userAction != 'H' && userAction != 'S');

			
			// If the user (H)its, the user gets a card. If the user (S)tands, 
			// the dealer gets a chance to draw and the game ends
			if (userAction == 'S') {
				break;
			} else { // Give the user a card. If the user goes over 21, the user loses
				deck.deal(userHand, 1);
				System.out.println();
				System.out.println("User hits.");
				System.out.println();
				System.out.println("Your new hand is: ");
				System.out.println(userHand.showHand());
				
				if (userHand.getTotal() > 21) {
					System.out.println();
					System.out.println("You busted by going over 21.  You lose.");
					System.out.println("Dealer's other card was the " + dealerHand.cards.get(1));
					return false;
				}
			}

		} 

		// Here, the user has Stood with 21 or less. Now, it's the dealer's turn. 
		// Dealer draws cards until its total is greater than 16
		System.out.println();
		System.out.println("User stands.");
		System.out.println();
		System.out.println("Dealer's cards are: ");
		System.out.println(dealerHand.cards.get(0) + "\n" + dealerHand.cards.get(1) + "\n");
		while (dealerHand.getTotal() <= 16) {
			deck.deal(dealerHand, 1);
			System.out.println();
			System.out.println("Dealer hits and its hand is: ");
			System.out.println(dealerHand.showHand());
		}

		// Now we can choose a winner
		System.out.println();
		if (dealerHand.getTotal() > 21) {
			System.out.println("Dealer busted by going over 21. You win.");
			return true;
		} else {
			if (dealerHand.getTotal() == userHand.getTotal()) {
				System.out.println("Dealer wins on a tie. You lose.");
				return false;
			} else {
				if (dealerHand.getTotal() > userHand.getTotal()) {
					System.out.println("Dealer wins, " + dealerHand.getTotal()
							+ " points to " + userHand.getTotal());
					return false;
				} else {
					System.out.println("You win, " + userHand.getTotal()
							+ " points to " + dealerHand.getTotal());
					return true;
				}
			}
		}

	}

	public static void showHands(Hand userHand, Hand dealerHand){
		System.out.print("Dealer has the " + dealerHand.cards.get(0));
		System.out.print(" and the " + dealerHand.cards.get(1));
		System.out.println();
	
		System.out.print("User has the " + userHand.cards.get(0));
		System.out.print(" and the " + userHand.cards.get(1));
		System.out.println();
	}
}