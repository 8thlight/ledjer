package ledjer;

import java.text.NumberFormat;

public class Ledger {
	private int balance;
	private int[] deposits = new int[10];
	private int currentDeposit = 0;

	public Ledger() {
		balance = 0;
	}

	public int getBalance() {
		return balance;
	}

	public void deposit(Deposit deposit) {
		deposits[currentDeposit++] = deposit.getAmount();
		balance += deposit.getAmount();
	}

	public String statement() {
		NumberFormat numberFormat = NumberFormat.getInstance();
		numberFormat.setMinimumFractionDigits(2);

		StringBuffer statement = writeDepositsToStatement(numberFormat);
		statement.append("Total: £" + numberFormat.format(balance));
		
		return statement.toString();
	}

	private StringBuffer writeDepositsToStatement(NumberFormat numberFormat) {
		StringBuffer statement = new StringBuffer();
		for (int deposit = 0; deposit < currentDeposit; deposit++) {
			statement.append("Deposit: £" + numberFormat.format(deposits[deposit]) + "\n");
		}
		return statement;
	}

}