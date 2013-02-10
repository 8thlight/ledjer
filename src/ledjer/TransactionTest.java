package ledjer;

import static org.junit.Assert.assertEquals;

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
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
		assertEquals(format.format(today), format.format(transaction.getDate()));
	}
	
	@Test
	public void sortsTransactionsByDate() {
		Transaction t1 = new TestTransaction(10, today);
		Transaction t2 = new TestTransaction(10, today);
		Transaction t3 = new TestTransaction(10, today);
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