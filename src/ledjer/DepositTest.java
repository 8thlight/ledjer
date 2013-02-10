package ledjer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Before;
import org.junit.Test;


public class DepositTest {
	private Calendar cal = new GregorianCalendar();
	private Date today = cal.getTime();
	private SimpleDateFormat format = new SimpleDateFormat("MMM d, yyyy");

	@Before
	public void setup() {
		Transaction.resetNextNumber();
		
	}
	@Test
	public void holdsAnAmount() {
		Deposit deposit = new Deposit(1000, today);
		assertEquals(1000, deposit.getAmount());
	}
	@Test
	public void createsStatement() {
		Transaction.resetNextNumber();
		Deposit deposit = new Deposit(2000, today);
		assertEquals(format.format(today) + " 1. Deposit: $20.00" + Transaction.newLine(), deposit.asStatement());
	}

	@Test
	public void implementsCloneable() {
		Deposit deposit = new Deposit(3000, today);
		Deposit clone = deposit.clone();
		assertEquals(deposit.getClass(), clone.getClass());
		assertEquals(deposit.getAmount(), clone.getAmount());
	}
	
	@Test
	public void depositsWithDifferentNumbersAreNotEqual() {
		Deposit deposit = new Deposit(3000, today);
		Deposit depositTwo = new Deposit(3000, today);
		
		assertFalse(deposit.equals(depositTwo));
	}
	
	@Test
	public void depositsWithDifferentDatesAreNotEqual() throws ParseException {
		Date otherDate = format.parse("Jan 1, 2001");
		Transaction.resetNextNumber();
		Deposit deposit = new Deposit(3000, today);
		Transaction.resetNextNumber();
		Deposit depositTwo = new Deposit(3000, otherDate);
		
		assertFalse(deposit.equals(depositTwo));
	}
	
	@Test
	public void depositsWithDifferentAmountsAreNotEqual() {
		Deposit deposit = new Deposit(3000, today);
		Deposit depositTwo = new Deposit(4000, today);
		
		assertFalse(deposit.equals(depositTwo));
	}
	
	@Test
	public void depositsAreNotEqualToPaymentsOfEqualAmount() {
		Deposit deposit = new Deposit(3000, today);
		Payment payment = new Payment(3000, today, "foo");
		assertFalse(deposit.equals(payment));
	}
	
	@Test
	public void clonesAreEqualToTheOriginal() {
		Deposit deposit = new Deposit(3000, today);
		Deposit clone = deposit.clone();
		
		assertTrue(deposit.equals(clone));
	}
}
