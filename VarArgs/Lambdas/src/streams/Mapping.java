package streams;
import static java.lang.System.*;
import static java.util.stream.Collectors.*;

import java.util.Arrays;
import java.util.List;

public class Mapping {

    private static final List<String> words = Arrays.asList(
        "Larger", "fleas", "have", "smaller", "fleas", "upon",
        "their", "backs", "to", "bite-em.", "Smaller", "fleas",
        "have", "smaller", "still", "and", "on", "ad-infinitum."
    );

    public static void main(final String... args) {
        out.println("\nList<String> words = Arrays.asList():\n" + words);
        getDishNamesAndCaloriesSorted();
        getDistinctWordsSorted();
        getDistinctWordLengthsSorted();
        printParallelArrays();
    }

    private static void getDishNamesAndCaloriesSorted() {
        out.println("\ngetDishNamesAndCaloriesSorted(): ");
        // Dish names
        out.println(Dish.menu.stream()
            .map(Dish::getName)
            .sorted()
            .collect(toList()));

        // Dish calories
        out.println(Dish.menu.stream()
            .map(Dish::getCalories)
            .sorted()
            .collect(toList()));
    }

    private static void getDistinctWordsSorted() {
        out.println("\ngetDistinctWordsSorted()");
        words.stream()
            .flatMap((final String line) -> Arrays.stream(line.split("")))
            .distinct()
            .sorted()
            .forEach(s -> out.print(s + ", "));
        out.println();
    }

    private static void getDistinctWordLengthsSorted() {
        out.println("\ngetDistinctWordLengthsSorted(): ");
        final List<Integer> wordLengths = words.stream()
            .distinct()
            .map(String::length)
            .distinct()
            .sorted()
            .collect(toList());
        out.println(wordLengths);
    }

    private static void printParallelArrays() {
        out.println("\nprintParallelArrays()");
        final var n1 = Arrays.asList(0, 1, 2, 3, 4);
        final var n2 = Arrays.asList(5, 6, 7, 8, 9);

        final var pairs = n1.stream()
            .flatMap((final Integer i) ->
                n2.stream().map((final Integer j) ->
                    new int[] { i, j }
            ))
            .filter(pair -> (pair[0] + pair[1]) % 2 == 0)
            .collect(toList());
        pairs.forEach(pair -> out.println("[" + pair[0] + ", " + pair[1] + "]"));
    }
}
