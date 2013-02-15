package ledjer;

import static org.junit.Assert.*;
import java.io.File;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Before;
import org.junit.Test;

public class LedgerTest {

	private Ledger ledger;
	private Calendar cal;
	private Date today;
	private SimpleDateFormat format = new SimpleDateFormat("MMM d, yyyy");
	
	@Before
	public void setup() {
		ledger = new Ledger();
		Transaction.resetNextNumber();
		cal = new GregorianCalendar();
		today = cal.getTime();
	}
	
	@Test
	public void startsWithZeroBalance() {
		assertEquals(0, ledger.getBalance());
	}
	
	@Test
	public void depositAddsToBalance() {
		ledger.deposit(new Deposit(100, today));
		assertEquals(100, ledger.getBalance());
	}
	
	@Test
	public void paymentSubtractsFromBalance() {
		Transaction deposit = new Deposit(100, today);
		Payment payment = new Payment(50, today, "foo");
		ledger.deposit(deposit);
		ledger.pay(payment);
		assertEquals(50, ledger.getBalance());
	}
	
	@Test
	public void statementIncludesSortedPaymentsAndDepositsHistory() throws ParseException {
		Date dateOne = format.parse("Jan 1, 2013");
		Date dateTwo = format.parse("Jan 2, 2013");
		Date dateThree = format.parse("Jan 3, 2013");
		
		ledger.deposit(new Deposit(1000, dateThree));
		ledger.pay(new Payment(500, dateTwo,"foo"));
		ledger.pay(new Payment(100, dateOne, "bar"));

		String expectedStatement = "Jan 1, 2013 3. Payment to bar: ($1.00)" + Transaction.newLine() + 
								   "Jan 2, 2013 2. Payment to foo: ($5.00)" + Transaction.newLine() +
								   "Jan 3, 2013 1. Deposit: $10.00" + Transaction.newLine() + 
								   "Total: $4.00";
		assertEquals(expectedStatement, ledger.statement());
	}
	
	@Test(expected = NegativeBalanceException.class)
	public void doesNotAllowNegativeBalance() {
		ledger.pay(new Payment(1, today, "payee"));
	}
	
    @Test
    public void emptyLedgersAreEqual() {
        assertEquals(ledger, new Ledger());
    }
    
    @Test
    public void equalityBasedOnInstanceOfTarget() {
        assertFalse(ledger.equals(new Object()));
    }
    
  @Test
  public void ledgersNotEqualIfNumberOfTransactionsDifferent() {
      Ledger ledgerTwo = new Ledger();
      ledger.deposit(new Deposit(100, today));
      assertFalse(ledger.equals(ledgerTwo));
  }
 
  @Test
  public void ledgersNotEqualIfTransactionsAreNotEqual() {
	  Deposit deposit = new Deposit(200, today);
      Payment paymentOne = new Payment(100, today, "foo");
      Payment paymentTwo = new Payment(100, today, "bar");
	  Ledger ledgerTwo = new Ledger();
      ledger.deposit(deposit);
      ledgerTwo.deposit(deposit);
      ledger.pay(paymentOne);
      ledgerTwo.pay(paymentTwo);
      
      assertFalse(ledger.equals(ledgerTwo));
  }
  
  @Test
  public void ledgersAreEqualIfTransactionHistoryIsEqual() {
	  Deposit deposit = new Deposit(400, today);
      Payment paymentOne = new Payment(100, today, "foo");
      Payment paymentTwo = new Payment(100, today, "bar");
	  Ledger ledgerTwo = new Ledger();
      ledger.deposit(deposit);
      ledgerTwo.deposit(deposit);
      ledger.pay(paymentOne);
      ledger.pay(paymentTwo);
      ledgerTwo.pay(paymentOne);
      ledgerTwo.pay(paymentTwo);
      
      assertEquals(ledger, ledgerTwo);
  }
  
  @Test
  public void transactionsAndBalanceClonedWhenCloningLedgers() throws CloneNotSupportedException {
	  Deposit deposit = new Deposit(400, today);
      Payment paymentOne = new Payment(100, today, "foo");
      Payment paymentTwo = new Payment(100, today, "bar");
      ledger.deposit(deposit);
      ledger.pay(paymentOne);
      ledger.pay(paymentTwo);
      
      assertEquals(ledger.getBalance(), ledger.clone().getBalance());
      assertEquals(ledger, ledger.clone());
      assertNotSame(ledger, ledger.clone());
  }

  @Test
  public void saving() throws Exception
  {
    final MemoryPersister memoryPersister = new MemoryPersister();
    Ledger.setPersister(memoryPersister);
    ledger.save();
    assertSame(ledger, memoryPersister.savedObject);
  }

  @Test
  public void loads() throws Exception
  {
    final MemoryPersister memoryPersister = new MemoryPersister();
    Ledger.setPersister(memoryPersister);
    memoryPersister.savedObject = ledger;

    assertSame(ledger, Ledger.load());
  }
}
