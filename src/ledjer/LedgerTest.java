package ledjer;

import org.junit.Before;
import org.junit.Test;

import static org.testng.AssertJUnit.assertEquals;

public class LedgerTest {

	@Before
	public void setup() {
	}
	
	@Test
    public void startsWithNoBalance() {
        Ledger ledger = new Ledger();
        assertEquals(0, ledger.getBalance());
    }

    @Test
    public void canAddDepositToBalance() {
        Ledger ledger = new Ledger();
        ledger.deposit(new Deposit(50));
        assertEquals(50, ledger.getBalance());
    }

    @Test
    public void itPrintsAStatement() {
        Ledger ledger = new Ledger();
        ledger.deposit(new Deposit(75));
        assertEquals("Your balance is 75", ledger.statement());
    }
}