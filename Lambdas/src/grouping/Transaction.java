package grouping;

public class Transaction {

    private final GroupingTransactions.Currency currency;
    private final double value;

    public Transaction(GroupingTransactions.Currency currency, double value) {
        this.currency = currency;
        this.value = value;
    }

    public GroupingTransactions.Currency getCurrency() {
        return currency;
    }

    public double getValue() {
        return value;
    }

    @Override
    public String toString() {
        return currency + " " + value;
    }
}