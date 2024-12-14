package grouping;

import static java.lang.System.*;
import static java.util.stream.Collectors.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import streams.Transaction;

public class GroupingTransactions {

    public enum Currency {
        EUR, USD, JPY, GBP, CHF
    }

    protected static final List<Transaction> txList = Arrays.asList(
        new Transaction(Currency.EUR, 1500.0),
        new Transaction(Currency.USD, 2300.0),
        new Transaction(Currency.GBP, 9900.0),
        new Transaction(Currency.EUR, 1100.0),
        new Transaction(Currency.JPY, 7800.0),
        new Transaction(Currency.CHF, 6700.0),
        new Transaction(Currency.EUR, 5600.0),
        new Transaction(Currency.USD, 4500.0),
        new Transaction(Currency.CHF, 3400.0),
        new Transaction(Currency.GBP, 3200.0),
        new Transaction(Currency.USD, 4600.0),
        new Transaction(Currency.JPY, 5700.0),
        new Transaction(Currency.EUR, 6800.0)
    );

    public static void main(String... args) {
        //       <Key       Value>
        Map<Currency, List<Transaction>> txByCurrency =
            txList.stream().collect(groupingBy(Transaction::getCurrency));
        out.println("\n\n" + txByCurrency + "\n\n");
    }
}