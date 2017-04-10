// This class represents a single Card; each with both a suit and a value

public class Card {
	private Suit suit;
	private FaceValue value;
	
	public Card(Suit suit, FaceValue value) {
		this.suit = suit;
		this.value = value;
	}
	
	public String getSuit() {
		return suit.printSuit();
	}
	
	public String printValue() {
		return value.printValue();
	}
	
	public int getValue() {
		return value.getValue();
	}
	
	public String toString() {
		String str = "";
		str += value.printValue() + " of " + suit.printSuit();
		
		return str;
	}
}
