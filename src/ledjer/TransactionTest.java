package ledjer;

import org.junit.Test;

import static org.testng.AssertJUnit.assertEquals;

public class TransactionTest {
    @Test
    public void hasTransactionConstructor() {
        Transaction transaction = new Transaction(500);
        assertEquals(500, transaction.getAmount());
    }
}
