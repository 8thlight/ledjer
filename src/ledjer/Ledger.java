package ledjer;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Ledger implements Cloneable, Serializable {
	private static final long serialVersionUID = 1L;
	private int balance;
	private List<Transaction> transactions;
	
	public Ledger() {
		balance = 0;
		transactions = new LinkedList<Transaction>();
	}
	
	public int getBalance() {
		return balance;
	}

	public void deposit(Transaction deposit) {
		balance += deposit.getAmount();
		addTransaction(deposit);
	}

	public void pay(Transaction payment) {
		checkForAvailableFunds(payment);
		balance -= payment.getAmount();
		addTransaction(payment);
	}

	public String statement() {
		String statement = "";
		Collections.sort(transactions);
		for (Transaction transaction : transactions) {
			statement += transaction.asStatement();
		}
		statement += formatTotal();
		return statement;
	}
	
	@Override
	public Ledger clone() {
		Ledger clone = new Ledger();
		clone.balance = this.balance;
		for (Transaction transaction : transactions)
				clone.transactions.add((Transaction) transaction.clone());
		return clone;
	}
	
	@Override
	public boolean equals(Object object) {
		if (!(object instanceof Ledger))
			return false;
		Ledger other = (Ledger) object;
		return isTransactionHistoryEqual(other);
	}

	public boolean isTransactionHistoryEqual(Ledger other) {
		if (transactions.size() != other.transactions.size())
			return false;
		for (int i = 0; i < transactions.size(); i++) {
			if (!transactions.get(i).equals(other.transactions.get(i)))
				return false;
		}
		return true;
	}
	
	private void checkForAvailableFunds(Transaction payment) {
		if (balance - payment.getAmount() < 0) {
			throw new NegativeBalanceException();
		}
	}
	
	private void addTransaction(Transaction transaction) {
		transactions.add(transaction);
	}
	
	private String formatTotal() {
		return "Total: " + Transaction.formattedAmount(getBalance());
	}

	public void save() {
		try {
			ObjectOutputStream output = 
					new ObjectOutputStream(
							new FileOutputStream("ledger.dump"));
			output.writeObject(this);
			output.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} 
	}
	
	public static Ledger load() {
		try {
			ObjectInputStream input = 
					new ObjectInputStream(
					new FileInputStream("ledger.dump"));
			Ledger result = (Ledger) input.readObject();
			input.close();
			return result;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}