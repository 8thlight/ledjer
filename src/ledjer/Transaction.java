package ledjer;

import java.io.Serializable;

public abstract class Transaction implements Cloneable, Serializable {
	private static final long serialVersionUID = 1L;
	private static final int STARTING_NUMBER = 1;
	private int amount;
	private int number;
	private static int nextNumber = STARTING_NUMBER;
	
	public Transaction(int amount) {
		this.amount = amount;
		this.number = nextNumber++;
	}
	
	public int getAmount() {
		return amount;
	}
	
	public abstract String asStatement();
	
	public static String formattedAmount(int amount) {
		int dollar = amount / 100;
		int cents = amount % 100;
		return String.format("$%1d.%02d", dollar, cents);
	}
	
	public static String newLine() {
		return System.getProperty("line.separator");
	}

	public int getNumber() {
		return number;
	}
	
	@Override
	protected Transaction clone() {
		try {
			return (Transaction)super.clone();
		} catch (CloneNotSupportedException e) {
			throw new RuntimeException(e);
		}
	}

	public static void resetNextNumber() {
		nextNumber = STARTING_NUMBER;
	}

}