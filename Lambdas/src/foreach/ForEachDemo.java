package foreach;

import static java.lang.System.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Show How to use the forEach() statement to:
 *      Loop over a collection
 *      Filter a collection
 *      Transform a collection
 *      Run a loop in parallel
 */
public class ForEachDemo {

    static final String FMT = "%n%s%n";

    static final List<String> words = new ArrayList<>(
        Arrays.asList(
            "larger", "fleas", "have", "smaller", "fleas", "upon", "their", "backs",
            "to", "bite-em. ", "Smaller", "fleas", "have", "smaller", "still,", "and",
            "on", "ad-infinitum."
        )
    );

    public static void main(final String... args) {
        out.printf(FMT, "Loop over all elements of a list using:");

        out.printf(FMT, "LAMBDA EXPRESSION: ");
        words.forEach(s -> out.print(s + ", "));

        out.printf(FMT, "METHOD REFERENCE: ");
        words.forEach(out::print);

        out.printf(FMT, "Filter and print words beginning with 't'");
        words.stream()
            .filter(s -> s.startsWith("t"))
            .forEach(out::println);

        out.printf(FMT, "Filter and print distinct strings with a length() >= " + 7);
        words.stream()
            .filter(s -> s.length() >= 7)
            .distinct()
            .forEach(out::println);

        out.printf(FMT, "Print length of each string (distinct and sorted) with mapToInt()");
        words.stream()
            .mapToInt(String::length)
            .distinct()
            .sorted()
            .forEach(s -> out.print(s + ", "));

        out.printf(FMT, "Calculate the distinct sum of the length of all strings with mapToInt()");
        out.println(words.stream()
            .mapToInt(String::length)
            .distinct()
            .sum());
        out.println();
    }
}