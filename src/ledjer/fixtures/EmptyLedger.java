package ledjer.fixtures;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import ledjer.Deposit;
import ledjer.Ledger;
import ledjer.Payment;
import ledjer.Transaction;

public class EmptyLedger {
	private static final String DATE_FORMAT = "MMM d, yyyy";
	private Class<? extends Exception> exceptionType;
	private Calendar cal = new GregorianCalendar();
	private Date today = cal.getTime();

	public EmptyLedger() {
		Transaction.resetNextNumber();
		Context.ledger = new Ledger();
	}
	
	public String generateStatement() {
		return Context.ledger.statement();
	}
	
	public void depositThisManyTimes(int depositAmount, int numberOfDeposits) {
		for (int i=0; i < numberOfDeposits; i++) {
			Context.ledger.deposit(new Deposit(depositAmount, today));
		}
	}
	
	public int balance() {
		return Context.ledger.getBalance();
	}
	
	public void makePaymentAndCatchException(int paymentAmount) {
		try {
			Context.ledger.pay(new Payment(paymentAmount, today, "Anyone"));
		}
		catch (Exception e) {
			exceptionType = e.getClass();
		}
	}
	
	public boolean depositCentsOn(int amount, String depositDate) throws ParseException {
		Date date = new SimpleDateFormat(DATE_FORMAT).parse(depositDate);
		Context.ledger.deposit(new Deposit(amount, date));
		return true;
	}
	
	public boolean payCentsToOn(int amount, String payee, String paymentDate) throws ParseException {
		Date date = new SimpleDateFormat(DATE_FORMAT).parse(paymentDate);
		Context.ledger.pay(new Payment(amount, date, payee));
		return true;
	}
	
	public boolean save() {
		Context.ledger.save();
		return true;
	}
	
	public boolean load() {
		Context.ledger = Ledger.load();
		return true;
	}
	
	public String exceptionClass() {
		if (exceptionType == null)
			return "No exception caught";
		return exceptionType.toString();
	}
}
