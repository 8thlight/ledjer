package ledjer;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;

import org.junit.Test;

public class TransactionTest {
	private Calendar cal = new GregorianCalendar();
	private Date today = cal.getTime();
	private SimpleDateFormat format = new SimpleDateFormat("MMM d, yyyy");

	private class TestTransaction extends Transaction {

		private static final long serialVersionUID = 1L;
		public TestTransaction(int amount, Date date) {
			super(amount, date);
		}
		@Override
		public String asStatement() {
			return null;
		}
	}
	
	@Test
	public void constructsTransaction() {
		Transaction.resetNextNumber();
		Transaction transaction = new TestTransaction(1000, today);
		assertEquals(1000, transaction.getAmount());
		assertEquals(1, transaction.getNumber());
	}
	
	@Test
	public void assignsNumbersSequentially() {
		Transaction.resetNextNumber();
		Transaction t1 = new TestTransaction(10, today);
		Transaction t2 = new TestTransaction(10, today);
		Transaction t3 = new TestTransaction(10, today);
		assertEquals(1, t1.getNumber());
		assertEquals(2, t2.getNumber());
		assertEquals(3, t3.getNumber());
	}
	
	@Test
	public void resetNextNumber() {
		new TestTransaction(10, today);
		new TestTransaction(10, today);
		new TestTransaction(10, today);
		Transaction.resetNextNumber();
		assertEquals(1, new TestTransaction(10, today).getNumber());
	}
	
	@Test
	public void assignsTodaysDate() {
		Transaction transaction = new TestTransaction(1000, today);
		assertEquals(format.format(today), format.format(transaction.getDate()));
	}
	
	@Test
	public void sortsTransactionsByDate() throws ParseException {
		Date dateOne = format.parse("Jan 1, 2013");
		Date dateTwo = format.parse("Jan 2, 2013");
		Date dateThree = format.parse("Jan 3, 2013");
		
		Transaction t1 = new TestTransaction(10, dateOne);
		Transaction t2 = new TestTransaction(10, dateTwo);
		Transaction t3 = new TestTransaction(10, dateThree);
		LinkedList<Transaction> transactions = new LinkedList<Transaction>();
		transactions.add(t3);
		transactions.add(t2);
		transactions.add(t1);
		Collections.sort(transactions);
		assertEquals(t1, transactions.get(0));
		assertEquals(t2, transactions.get(1));
		assertEquals(t3, transactions.get(2));
	}
	
}