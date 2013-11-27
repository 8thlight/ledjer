package ledjer;


public class Payment {
    private int amount;
    private String payee;

    public Payment(int amount, String payee)
    {
        this.amount = amount;
        this.payee = payee;
    }

    public int getAmount() {
        return amount;
    }

    public String getPayee() {
        return payee;
    }


}
