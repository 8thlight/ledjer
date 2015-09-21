package ledjer.features;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import ledjer.Deposit;
import ledjer.Ledger;
import static org.assertj.core.api.Assertions.assertThat;

public class StepDefinitions
{
  private Ledger ledger;

  @Given("^an empty ledger$")
  public void anEmptyLedger()
  {
    ledger = new Ledger();
  }

  @When("^a deposit of (\\d+) is made$")
  public void aDepositOfIsMade(int amount)
  {
    ledger.deposit(new Deposit(amount));
  }

  @Then("^the balance is (\\d+)$")
  public void theBalanceIs(int expectedBalance)
  {
    assertThat(ledger.getBalance()).isEqualTo(expectedBalance);
  }

  @Then("^the statement contains$")
  public void theStatementContains(String expectedStatement)
  {
    assertThat(ledger.statement()).isEqualTo(expectedStatement);
  }
}
