package ledjer;

public class Ledger {

	private int balance;
    private Transaction transaction;

	public Ledger() {
		balance = 0;
	}
	
	public int getBalance() {
		return balance;
	}

	public void deposit(Deposit deposit) {
        balance += deposit.getAmount();
	}

    public void pay(Transaction payment) {
        balance -= payment.getAmount();
    }

	public String statement() {
        String statement = "";
        statement += transaction.asStatement();
        return statement;
	}


}