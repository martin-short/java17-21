package streams;

import grouping.GroupingTransactions.Currency;

public class Transaction {

    private Trader trader;
    private int year = 0;
    private int value = 0;

    public Transaction(final Trader trader, final int year, final int value) {
        this.trader = trader;
        this.year   = year;
        this.value  = value;
    }

    public Transaction(Currency eur, double d) {
    }

    public Trader getTrader() {
        return this.trader;
    }

    public int getYear() {
        return this.year;
    }

    public int getValue() {
        return this.value;
    }

    public String toString() {
        return "{" + this.trader + ", "+ "year: " + this.year + ", " + "value:" + this.value + "}";
    }

    public Currency getCurrency() {
        return Currency.USD;
    }
}