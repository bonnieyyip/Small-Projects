import java.util.Random;

// This class represents an entire deck of 52 cards
public class Deck extends Hand {
	Random rand = new Random();
	
	// Uses a nested loop to loop through values and suits
	public void populate() {
		for(Suit suit: Suit.values()) {
			for(FaceValue value: FaceValue.values()) {
				Card card = new Card(suit, value);
				this.add(card); 
			}
		}
	}
	
	// Uses the Fisher-Yates shuffle method
	public void shuffle() {
		for(int i = cards.size() - 1; i > 0; i--) {
			int pick = rand.nextInt(i);
			
			Card randCard = cards.get(pick); 
			Card lastCard = cards.get(i);
			cards.set(i, randCard);
			cards.set(pick, lastCard);
		}
	}
	
	// Deal to ALL the players-- deal to an array of hands
	public void deal(Hand[] hands, int perHand) {
		for(int i = 0; i < perHand; i++){
			for(int j = 0; j < hands.length; j++) {
				// Pull off top of the deck
				// Multiple hands in an array
				this.give(cards.get(0), hands[j]);
			}
		}
	}
	
	// Deal to ONE player
	public void deal(Hand hand, int perHand) {
		// Just loop through all of the cards in a hand
		// Deal to a single hand
		for(int i = 0; i < perHand; i++) {
			this.give(cards.get(0), hand);
		}
	}
}
