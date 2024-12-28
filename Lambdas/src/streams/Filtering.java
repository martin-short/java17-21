package streams;

import static java.lang.System.*;
import static java.util.stream.Collectors.*;

import java.util.Arrays;
import java.util.List;

public class Filtering {

    public static void main(final String... args) {
        out.printf("%n%s", "Filtering with predicate");
        final List<Dish> vegi = Dish.menu.stream()
            .filter(Dish::isVegetarian)
            .collect(toList());
        vegi.forEach(s -> out.print(s + ", "));

        out.printf("%n%n%s", "Filtering unique 'even' elements with: distinct()");
        final List<Integer> nums = Arrays.asList(
            1,
            1, 2, 3,
            1, 2, 3, 4, 5,
            1, 2, 3, 4, 5, 6, 7,
            1, 2, 3, 4, 5, 6, 7, 8,
            1, 2, 3, 4, 5, 6, 7,
            1, 2, 3, 4, 5,
            1, 2, 3,
            1
        );

        nums.stream()
            .distinct()
            .filter(i -> i % 2 == 0)
            .collect(toList());
        nums.forEach(s -> out.print(s + ", "));

        out.printf("%n%n%s", "Truncating a stream to 3 items with: limit(3) and calories > 300");
        final List<Dish> limit3 = Dish.menu.stream()
            .filter(d -> d.getCalories() > 300)
            .limit(3)
            .collect(toList());
        limit3.forEach(s -> out.print(s + ", "));

        out.printf("%n%s", "Skipping 3 elements with: skip(3) and calories > 300");
        final List<Dish> skip3 = Dish.menu.stream()
            .filter(d -> d.getCalories() > 300)
            .skip(3)
            .collect(toList());
        skip3.forEach(s -> out.print(s + ", "));
        out.printf("%n");
    }
}
