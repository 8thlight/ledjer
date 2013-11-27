package ledjer;

import org.junit.Test;

import static org.testng.AssertJUnit.assertEquals;

public class TransactionTest {
    private class TestTransaction extends Transaction {
        public TestTransaction(int amount) {
            super(amount);
        }

        @Override
        public String asStatement() {
            return null;
        }
    }
    @Test
    public void hasTransactionConstructor() {
        Transaction transaction = new Transaction(500);
        assertEquals(500, transaction.getAmount());
    }
}
