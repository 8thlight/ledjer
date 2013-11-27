package ledjer;

import org.junit.Test;

import static org.testng.AssertJUnit.assertEquals;

public class DepositTest {

    @Test
    public void itHasAnAmount() {
        Deposit deposit = new Deposit(100);
        assertEquals(100, deposit.getAmount());
    }

}