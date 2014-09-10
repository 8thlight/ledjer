package ledjer;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class DepositTest
{
  @Test
  public void getAmount() throws Exception
  {
    Deposit deposit = new Deposit(42);
    assertEquals(42, deposit.getAmount());
  }

  @Test
  public void asStatement() throws Exception
  {
    assertEquals("Deposit: $1.23\n", new Deposit(123).asStatement());
  }
}