package ledjer;

public class Ledger {
	
	private int balance;
	
	public Ledger() {
		balance = 0;
	}
	
	public int getBalance() {
		return balance;
	}

	public void deposit(Deposit deposit) {
        balance += deposit.getAmount();
	}

	public String statement() {
		return "Your balance is " + balance;
	}
}