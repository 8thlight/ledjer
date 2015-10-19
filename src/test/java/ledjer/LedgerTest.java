package ledjer;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class LedgerTest {

	@Test
	public void balanceIs0WhenNewLedgerCreated() {
		Ledger ledger = new Ledger();
		assertThat(ledger.getBalance()).isEqualTo(0);
	}

	@Test
	public void statementShowsTotalOfZeroWhenNewLedgerCreated() {
		Ledger ledger = new Ledger();

		String expectedStatement = "Total: £0.00";
		assertThat(ledger.statement()).isEqualTo(expectedStatement);
	}

	@Test
	public void depositing10UpdatesBalanceTo10() {
		Ledger ledger = new Ledger();
		ledger.deposit(new Deposit(10));

		assertThat(ledger.getBalance()).isEqualTo(10);
	}

	@Test
	public void statementShowsDepositOfTenAndBalanceOfTen() {
		Ledger ledger = new Ledger();
		ledger.deposit(new Deposit(10));

		String expectedStatement = "Deposit: £10.00\nTotal: £10.00";

		assertThat(ledger.statement()).isEqualTo(expectedStatement);
	}

	@Test
	public void statementShowsMultipleDepositsMadeWithTotalBalance() {
		Ledger ledger = new Ledger();
		ledger.deposit(new Deposit(4));
		ledger.deposit(new Deposit(2));
	
		String expectedStatement = "Deposit: £4.00\nDeposit: £2.00\nTotal: £6.00";
		
		assertThat(ledger.statement()).isEqualTo(expectedStatement);
	}

}