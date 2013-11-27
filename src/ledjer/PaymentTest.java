package ledjer;

import org.junit.Test;

import static org.testng.AssertJUnit.assertEquals;

public class PaymentTest {

    @Test
    public void hasAnAmount() {
        Payment payment = new Payment(500, "alice");
        assertEquals(500, payment.getAmount());
    }

    @Test
    public void hasAPayee() {
        Payment payment = new Payment(500, "alice");
        assertEquals("alice", payment.getPayee());
    }

    @Test
    public void hasAStatement() {
        Payment payment = new Payment(100, "riley");
        assertEquals("Payment to riley: $10.00" + Transaction.newLine(), payment.asStatement());
    }


}
