package streams;

import static java.lang.System.*;
import static java.util.Comparator.*;
import static java.util.stream.Collectors.*;

import java.util.Arrays;
import java.util.List;

public class PuttingIntoPractice {

    public static void main(final String... args) {
        final Trader adam = new Trader("Adam", "Cambridge");
        final Trader eve  = new Trader("Eve",  "Milan");
        final Trader cain = new Trader("Cain", "Cambridge");
        final Trader able = new Trader("able", "Cambridge");

        final List<Transaction> transactions = Arrays.asList(
            //  Transaction(Trader, Year, Value)
            new Transaction(able, 2019, 300),
            new Transaction(able, 2020, 360),
            new Transaction(adam, 2020, 999),
            new Transaction(adam, 2019, 900),
            new Transaction(eve,  2020, 750),
            new Transaction(eve,  2020, 700),
            new Transaction(cain, 2020, 950),
            new Transaction(cain, 2019, 975)
        );

        out.println("\nGet and sort all 2019 transactions.");
        final List<Transaction> tx2019 = transactions.stream()
            .filter(tx -> tx.getYear() == 2019)
            .sorted(comparing(Transaction::getValue))
            .collect(toList());
        out.println(tx2019);

        out.println("\nGet all unique cities where traders work?");
        final List<String> cities = transactions.stream()
            .map(tx -> tx.getTrader().getCity())
            .distinct()
            .collect(toList());
        out.println(cities);

        out.println("\nGet all traders from Cambridge and sort by name.");
        final List<Trader> traders = transactions.stream()
            .map(Transaction::getTrader)
            .filter(trader -> trader.getCity().equals("Cambridge"))
            .distinct()
            .sorted(comparing(Trader::getName))
            .collect(toList());
        out.println(traders);

        out.println("\nReturn a string of all traders names sorted.");
        final String traderStr = transactions.stream()
            .map(tx -> tx.getTrader().getName())
            .distinct()
            .sorted()
            .reduce("", (n1, n2) -> n1 + n2);
        out.println(traderStr);

        out.println("\nAre there traders based in Milan?");
        final boolean milan = transactions.stream()
            .anyMatch(tx -> tx.getTrader().getCity().equals("Milan"));
        out.println(milan);

        out.println("\nUpdate all transactions. Move the traders from Milan to Cambridge.");
        transactions.stream()
            .map(Transaction::getTrader)
            .filter(trader -> trader.getCity().equals("Milan"))
            .forEach(trader -> trader.setCity("Cambridge"));
        out.println(transactions);

        out.println("\nWhat's the highest value in all transactions?");
        final int hiVal = transactions.stream()
            .map(Transaction::getValue)
            .reduce(0, Integer::max);
        out.println(hiVal);
    }
}