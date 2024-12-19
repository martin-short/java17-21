package streambasic;

import static java.lang.System.*;
import static java.util.Comparator.*;
import static java.util.stream.Collectors.*;

import java.util.List;

public class StreamBasic {

    private static final int CAL_DELIM = 500;

    public static void main(final String... args) {
        final List<String>  lowCalDishesByName = getLowCalDishesByName(Dish.menu);
        final List<Integer> highCalDishesByCal = getHighCalDishesByCal(Dish.menu);

        out.println(
            "\nLow Calorie Dishes By Name: " + lowCalDishesByName +
            "\n-------------------------------------------------------------------" +
            "\nHigh Calorie Dishes By Calories: " + highCalDishesByCal);
    }

    public static List<String> getLowCalDishesByName(final List<Dish> dishes) {
        return dishes.stream()
            .filter(d -> d.getCalories() <= CAL_DELIM)
            .sorted(comparing(Dish::getName))
            .map(Dish::getName)
            .collect(toList());
    }

    public static List<Integer> getHighCalDishesByCal(final List<Dish> dishes) {
        return dishes.stream()
            .filter(d -> d.getCalories() > CAL_DELIM)
            .sorted(comparing(Dish::getCalories))
            .map(Dish::getCalories)
            .collect(toList());
    }
}