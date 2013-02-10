package ledjer;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Deposit extends Transaction {
	private static final long serialVersionUID = 1L;

	public Deposit(int amount, Date date) {
		super(amount, date);
	}
	
	@Override
	public String asStatement() {
		SimpleDateFormat format = new SimpleDateFormat("MMM d, yyyy");
		return format.format(getDate()) + " " + getNumber() + ". Deposit: " + formattedAmount(getAmount()) + newLine();
	}
	
	@Override
	public Deposit clone() {
		return (Deposit) super.clone();
	}
	
	@Override
	public boolean equals(Object object) {
		if (!(object instanceof Deposit))
			return false;
		return (this.getAmount() == ((Deposit) object).getAmount());
	}
}
