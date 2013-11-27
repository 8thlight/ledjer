package ledjer;

public class Deposit extends Transaction{

	public Deposit(int amount) {
        super(amount);
	}

    @Override
    public String asStatement() {
        return "Deposit: " + formattedAmount(getAmount()) + newLine();
    }
}

