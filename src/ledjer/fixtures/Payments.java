package ledjer.fixtures;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import ledjer.Payment;

public class Payments {
	private static final String DATE_FORMAT = "MMM d, yyyy";
	private int amount;
	private String payee;
	private String dateString;
	private Payment payment;
	
	public Payments() {
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	public void setPayee(String payee) {
		this.payee = payee;
	}
	
	public void setDate(String date) {
		this.dateString = date;
	}
	
	public int balance() {
		return Context.ledger.getBalance();
	}
	
	public int number() {
		return payment.getNumber();
	}
	
	public void execute() throws ParseException {
		Date date = new SimpleDateFormat(DATE_FORMAT).parse(dateString);
		payment = new Payment(amount, date, payee);
		Context.ledger.pay(payment);
	}
}
