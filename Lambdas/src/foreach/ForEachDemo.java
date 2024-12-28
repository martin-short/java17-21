package foreach;

import static java.lang.System.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Use the forEach() statement to:
 *   . Loop over a collection
 *   . Filter a collection
 *   . Transform a collection
 *   . Loop over a collection in parallel
 */
public class ForEachDemo {

    static final String FMT = "%n%s%n";

    static final List<String> words = new ArrayList<>(
        Arrays.asList("larger", "fleas", "have", "smaller", "fleas", "upon",
                "their", "backs", "to", "bite", "them. ", "Smaller", "fleas",
                "have", "smaller", "still,", "and", "on", "ad-infinitum."
        )
    );

    public static void main(final String... args) {
        out.printf(FMT, "Loop over all elements of a list with:");

        out.printf(FMT, "A LAMBDA EXPRESSION: ");
        words.forEach(s -> out.print(s + ", "));

        out.println();
        out.printf(FMT, "A METHOD REFERENCE: ");
        words.forEach(out::print);

        out.printf(FMT, "Filter/Print words that start with the letter 't'");
        words.stream()
             .filter(s -> s.startsWith("t"))
             .forEach(out::println);

        out.printf(FMT, "Filter/Print DISTINCT strings with a length() >= " + 7);
        words.stream()
             .filter(s -> s.length() >= 7)
             .distinct()
             .forEach(out::println);

        out.printf(FMT, "Print length of each string (DISTINCT/SORTED) with mapToInt()");
        words.stream()
             .mapToInt(String::length)
             .distinct()
             .sorted()
             .forEach(s -> out.print(s + ", "));

        out.printf(FMT, "Calculate the DISTINCT SUM of the length of all strings with mapToInt()");
        out.println(words.stream()
           .mapToInt(String::length)
           .distinct()
           .sum());
        out.println();
    }
}

