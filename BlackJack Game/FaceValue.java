// Enum to represent the 13 different types of cards

public enum FaceValue {
	ACE(1, "Ace"), TWO(2, "2"), THREE(3, "3"),
	FOUR(4, "4"), FIVE(5, "5"), SIX(6, "6"),
	SEVEN(7, "7"), EIGHT(8, "8"), NINE(9, "9"),
	TEN(10, "10"), JACK(10, "Jack"), 
	QUEEN(10, "Queen"), KING(10, "King");
	
	private final int value;
	private final String stringValue;
	
	private FaceValue(int value, String stringValue) {
		this.value = value;
		this.stringValue = stringValue;
	}
	
	public int getValue() {
		return value;
	}
	
	public String printValue() {
		return stringValue;
	}
	
	
}
