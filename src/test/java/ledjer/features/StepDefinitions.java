package ledjer.features;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import ledjer.Deposit;
import ledjer.Ledger;
import static org.assertj.core.api.Assertions.assertThat;

public class StepDefinitions {
  private Ledger ledger;

  @Given("^an empty ledger$")
  public void an_empty_ledger() {
    ledger = new Ledger();
  }

  @When("^a deposit of (\\d+)p is made$")
  public void a_deposit_is_made(int amount) {
    ledger.deposit(new Deposit(amount));
  }

  @Then("^the balance is (\\d+)p$")
  public void the_balance_is(int expectedBalance) {
    assertThat(ledger.getBalance()).isEqualTo(expectedBalance);
  }

  @Then("^the statement contains$")
  public void the_statement_contains(String expectedStatement) {
    assertThat(ledger.statement()).isEqualTo(expectedStatement);
  }
}
