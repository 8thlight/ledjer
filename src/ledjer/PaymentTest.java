package ledjer;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PaymentTest {
	@Before
	public void setup() {
		Transaction.resetNextNumber();
	}

	@Test
	public void holdsAnAmount() {
		Payment payment = new Payment(100, "foo");
		assertEquals(100, payment.getAmount());
	}
	
	@Test
	public void holdsAPayee() {
		Payment payment = new Payment(100, "foo");
		assertEquals("foo", payment.getPayee());
	}
	
	@Test
	public void createsStatement() {
		Payment payment = new Payment(200, "amazon.com");
		assertEquals("1. Payment to amazon.com: ($2.00)" + Transaction.newLine(), payment.asStatement());
	}
	
	@Test
	public void paymentsWithSameAmountAndPayeeAreEqual() {
		Payment payment = new Payment(200, "amazon.com");
		Payment paymentTwo = new Payment(200, "amazon.com");
		assertEquals(payment, paymentTwo);
	}
	
	@Test
	public void paymentsWithSameAmountAndDifferentPayeeAreNotEqual() {
		Payment payment = new Payment(200, "amazon.com");
		Payment paymentTwo = new Payment(200, "newegg.com");
		assertFalse(payment.equals(paymentTwo));
	}
	
	@Test
	public void paymentsWithDifferentAmountAndSamePayeeAreNotEqual() {
		Payment payment = new Payment(200, "amazon.com");
		Payment paymentTwo = new Payment(300, "amazon.com");
		assertFalse(payment.equals(paymentTwo));
	}
	
	@Test
	public void notEqualIfNotInstanceOfPayment() {
		Payment payment = new Payment(200, "amazon.com");
		Object object = new Object();
		assertFalse(payment.equals(object));
	}
	
	@Test
	public void clonedPaymentsAreEqual() {
		Payment payment = new Payment(200, "amazon.com");
		Payment clone = payment.clone();
		assertEquals(payment, clone);
	}
}