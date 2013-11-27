package ledjer;

import org.junit.Before;
import org.junit.Test;

import static org.testng.AssertJUnit.assertEquals;

public class LedgerTest {
    private Ledger ledger;

	@Before
	public void setup() {
        ledger = new Ledger();
	}
	
	@Test
    public void startsWithNoBalance() {
        assertEquals(0, ledger.getBalance());
    }

    @Test
    public void canAddDepositToBalance() {
        Deposit deposit = new Deposit(100);
        ledger.deposit(deposit);
        assertEquals(100, ledger.getBalance());
    }

    @Test
    public void itPrintsAStatement() {
        ledger.deposit(new Deposit(75));
        assertEquals("Your balance is 75", ledger.statement());
    }

    @Test
    public void itMakesAPayment() {
        Deposit deposit = new Deposit(50);
        Payment payment = new Payment(25, "bob");
        ledger.deposit(deposit);
        ledger.payment(payment);
        assertEquals(25, ledger.getBalance());
    }

}
