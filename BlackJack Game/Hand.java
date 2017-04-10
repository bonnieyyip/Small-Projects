import java.util.ArrayList;

// This class represents a hand which is an array of cards

public class Hand {
	protected ArrayList<Card> cards; 
	
	public Hand() {
		cards = new ArrayList<Card>();
	}
	
	public void clear() {
		cards.clear();
	}
	
	public void add(Card newCard) {
		cards.add(newCard);
	}
	
	public int numberOfCards() {
		return cards.size();
	}
	
	public String showHand() {
		String str = "";
		
		for(Card c: cards) {
			str += c.toString() + "\n"; 
		}
		str += "Total Points = " + getTotal() + "\n";
		
		return str;
	}
	
	public boolean give(Card myCard, Hand otherHand) {
		if(!cards.contains(myCard)) { //if I don't have the card
			return false;
		} else {
			cards.remove(myCard);
			otherHand.add(myCard);
			return true;
		}
	}
	
	// Value of Aces change depending on what total value you have (either 1 or 11)
	public int getTotal() {
		int totalPoints = 0;
		boolean hasAce = false;
		
		for(int i = 0; i < cards.size(); i++) {
			totalPoints += cards.get(i).getValue();
			
			// Check to see if the card is an ace
			if(cards.get(i).printValue() == "Ace") {
				hasAce = true;
			}
			 
			// Make ace be worth 11 if total points is less than 11
			if(hasAce && totalPoints <= 11) {
				totalPoints += 10; //Since Ace is already equal to 1
			}
		}
		return totalPoints;
	}
}
