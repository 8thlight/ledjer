package ledjer;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class PaymentTest {
	private Calendar cal = new GregorianCalendar();
	private Date today = cal.getTime();
	
	@Before
	public void setup() {
		Transaction.resetNextNumber();
	}

	@Test
	public void holdsAnAmount() {
		Payment payment = new Payment(100, today, "foo");
		assertEquals(100, payment.getAmount());
	}
	
	@Test
	public void holdsAPayee() {
		Payment payment = new Payment(100, today, "foo");
		assertEquals("foo", payment.getPayee());
	}
	
	@Test
	public void createsStatement() {
		SimpleDateFormat format = new SimpleDateFormat("MMM d, yyyy");
		Transaction.resetNextNumber();
		Payment payment = new Payment(200, today, "amazon.com");
		assertEquals(format.format(today) + " 1. Payment to amazon.com: ($2.00)" + Transaction.newLine(), payment.asStatement());
	}
	
	@Test
	public void paymentsWithSameAmountAndPayeeAreEqual() {
		Payment payment = new Payment(200, today, "amazon.com");
		Payment paymentTwo = new Payment(200, today, "amazon.com");
		assertEquals(payment, paymentTwo);
	}
	
	@Test
	public void paymentsWithSameAmountAndDifferentPayeeAreNotEqual() {
		Payment payment = new Payment(200, today, "amazon.com");
		Payment paymentTwo = new Payment(200, today, "newegg.com");
		assertFalse(payment.equals(paymentTwo));
	}
	
	@Test
	public void paymentsWithDifferentAmountAndSamePayeeAreNotEqual() {
		Payment payment = new Payment(200, today, "amazon.com");
		Payment paymentTwo = new Payment(300, today, "amazon.com");
		assertFalse(payment.equals(paymentTwo));
	}
	
	@Test
	public void notEqualIfNotInstanceOfPayment() {
		Payment payment = new Payment(200, today, "amazon.com");
		Object object = new Object();
		assertFalse(payment.equals(object));
	}
	
	@Test
	public void clonedPaymentsAreEqual() {
		Payment payment = new Payment(200, today, "amazon.com");
		Payment clone = payment.clone();
		assertEquals(payment, clone);
	}
}
