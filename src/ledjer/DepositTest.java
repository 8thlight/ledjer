package ledjer;

import org.junit.Test;

import static org.testng.AssertJUnit.assertEquals;

public class DepositTest {

    @Test
    public void itHasAnAmount() {
        Deposit deposit = new Deposit(100);
        assertEquals(100, deposit.getAmount());
    }

    @Test
    public void hasAStatement() {
        Deposit deposit = new Deposit(75);
        assertEquals("Deposit: $7.50" + Transaction.newLine(), deposit.asStatement());
    }

}