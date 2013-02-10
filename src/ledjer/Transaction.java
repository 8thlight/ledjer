package ledjer;

import java.io.Serializable;
import java.util.Date;

public abstract class Transaction implements Cloneable, Serializable, Comparable<Transaction> {

	protected int number;
	private static final long serialVersionUID = 1L;
	private static final int STARTING_NUMBER = 1;
	private int amount;
	private Date date;
	private static int nextNumber = STARTING_NUMBER;
	
	public Transaction(int amount, Date date) {
		this.amount = amount;
		this.number = nextNumber++;
		this.date = date;
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
	
	public Date getDate() {
		return date;
	}
	
	public int compareTo(Transaction transaction) {
		int sortValue = 0;
		if (date.before(transaction.getDate())) {
			sortValue = -1;		
		} else {
			sortValue = 1;
		}
		return sortValue;
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
