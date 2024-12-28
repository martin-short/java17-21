package streams;

import static java.lang.System.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Reducing {

    public static void main(final String... args) {
        final List<Integer> numbs = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);

        final Optional<Integer> sum = numbs.stream().reduce(Integer::sum);
        final Optional<Integer> max = numbs.stream().reduce(Integer::max);
        final Optional<Integer> min = numbs.stream().reduce(Integer::min);
        final Optional<Integer> cal = Dish.menu.stream()
            .map(Dish::getCalories).reduce(Integer::sum);

        sum.ifPresent(out::println);
        max.ifPresent(out::println);
        min.ifPresent(out::println);
        out.print("Number of calories: "); cal.ifPresent(out::println);
    }
}