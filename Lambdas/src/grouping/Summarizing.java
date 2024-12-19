package grouping;

import static java.lang.System.*;
import static java.util.stream.Collectors.*;

import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.function.BinaryOperator;

public class Summarizing implements ISummarizing {

    private static final String SEPARATOR = ", ";

    public static void main(final String... args) {
        final Summarizing s = new Summarizing();

        out.println();
        out.println("howManyDishes                     : " + s.howManyDishes());
        out.println("getMostCaloricDish                : " + s.getMostCaloricDish());
        out.println("getMostCaloricDishUsingComparator : " + s.getMostCaloricDishUsingComparator());
        out.println("calculateTotalCalories            : " + s.calculateTotalCalories());
        out.println("calculateAverageCalories          : " + s.calculateAverageCalories());
        out.println("calculateMenuStatistics           : " + s.calculateMenuStatistics());
        out.println("getShortMenu                      : " + s.getShortMenu());
        out.println("getShortMenuCommaSeparated        : " + s.getShortMenuCommaSeparated());
        out.println();
    }

    // collect(counting())
    public long howManyDishes() {
        return Dish.menu.size();
    }

    // collect(reducing((d1, d2)
    public Dish getMostCaloricDish() {
        return Dish.menu.stream()
            .reduce((d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2)
            .get();
    }

    // Comparator.comparingInt(Dish::getCalories)
    public Dish getMostCaloricDishUsingComparator() {
        final Comparator<Dish> dishCaloriesComparator = Comparator.comparingInt(Dish::getCalories);
        final BinaryOperator<Dish> moreCaloricOf = BinaryOperator.maxBy(dishCaloriesComparator);
        return Dish.menu.stream()
            .reduce(moreCaloricOf)
            .get();
    }

    // collect(summingInt(Dish::getCalories))
    public int calculateTotalCalories() {
        return Dish.menu.stream()
            .mapToInt(Dish::getCalories)
            .sum();
    }

    // collect(averagingInt(Dish::getCalories))
    public Double calculateAverageCalories() {
        return Dish.menu.stream()
            .collect(averagingInt(Dish::getCalories));
    }

    // collect(summarizingInt(Dish::getCalories))
    public IntSummaryStatistics calculateMenuStatistics() {
        return Dish.menu.stream()
            .collect(summarizingInt(Dish::getCalories));
    }

    // map(Dish::getName).collect(joining())
    public String getShortMenu() {
        return Dish.menu.stream()
            .map(Dish::getName)
            .collect(joining());
    }

    // map(Dish::getName).collect(joining(", "))
    public String getShortMenuCommaSeparated() {
        return Dish.menu.stream()
            .map(Dish::getName)
            .collect(joining(SEPARATOR));
    }
}