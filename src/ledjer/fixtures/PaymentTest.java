package ledjer.fixtures;

import org.junit.Test;

import static org.testng.AssertJUnit.assertEquals;

public class PaymentTest {

    @Test
    public void itLetsYouPaySomeone() {
        Payment payment = new Payment();
        assertEquals("Alice", payment.getPayee("Alice"));
    }
}
