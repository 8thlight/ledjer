package ledjer.fixtures;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import ledjer.Deposit;
import ledjer.Transaction;

public class Deposits {
	private static final String DATE_FORMAT = "MMM d, yyyy";
	private Transaction deposit;
	private int amount;
	private String dateString;
	
	public Deposits() {
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	public void setDate(String date) {
		this.dateString = date;
	}
	
	public int balance() {
		return Context.ledger.getBalance();
	}
	
	public int number() {
		return deposit.getNumber();
	}
	
	public void execute() throws ParseException {
		Date date = new SimpleDateFormat(DATE_FORMAT).parse(dateString);
		deposit = new Deposit(amount, date);
		Context.ledger.deposit(deposit);
	}
}
