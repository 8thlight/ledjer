package ledjer;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Payment extends Transaction {
	private static final String DATE_FORMAT = "MMM d, yyyy";
	private static final long serialVersionUID = 1L;
	private String payee;
	
	public Payment(int amount, Date date, String payee) {
		super(amount, date);
		this.payee = payee;
	}
	
	public String getPayee() {
		return payee;
	}
	
	@Override
	public String asStatement() {
		SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
		return format.format(getDate()) + " " + getNumber() + ". Payment to " + getPayee() + ": (" + formattedAmount(getAmount()) +")" + Transaction.newLine();
	}
	
	@Override
	public boolean equals(Object object) {
		if (!(object instanceof Payment))
			return false;
		return (this.getAmount() == ((Payment) object).getAmount() && this.getPayee() == ((Payment) object).getPayee());
	}
	
	@Override
	public Payment clone() {
		return (Payment) super.clone();
	}
}
