package streams;

import static java.lang.System.*;
import static java.util.Comparator.*;
import static java.util.stream.Collectors.*;

import java.util.List;

public class StreamBasic {

    public static void main(final String... args) {
        getLowCalDishes(Dish.menu).forEach(out::println);
        out.println("--------------");
        getHighCalDishes(Dish.menu).forEach(out::println);
    }

    public static List<String> getLowCalDishes(final List<Dish> dishes) {
        return dishes.stream()
            .filter(d -> d.getCalories() <= 500)
            .sorted(comparing(Dish::getCalories))
            .map(Dish::getName)
            .collect(toList());
    }

    public static List<String> getHighCalDishes(final List<Dish> dishes) {
        return dishes.stream()
            .filter(d -> d.getCalories() > 500)
            .sorted(comparing(Dish::getCalories))
            .map(Dish::getName)
            .collect(toList());
    }
}