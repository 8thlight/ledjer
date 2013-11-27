package ledjer;


public class Payment extends Transaction {
    private String payee;

    public Payment(int amount, String payee)
    {
        super(amount);
        this.payee = payee;
    }

    public String getPayee() {
        return payee;
    }

    @Override
    public String asStatement() {
        return "Payment to " + getPayee() + ": $" + formattedAmount(getAmount()) + Transaction.newLine();
    }


}
