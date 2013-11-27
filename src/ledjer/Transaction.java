package ledjer;

public abstract class Transaction {
    private int amount;
    public Transaction(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public abstract String asStatement();

    public static String formattedAmount(int amount) {
        int dollar = amount / 10;
        int cents = amount % 10;
        return String.format("$%1d.%02d", dollar, cents);
    }

    public static String newLine() {
        return System.getProperty("line.separator");
    }


}

