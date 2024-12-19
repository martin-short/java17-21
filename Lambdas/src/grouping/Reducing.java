package grouping;

import java.util.Optional;

import static java.lang.System.*;

public class Reducing {

    public static void main(final String... args) {
        out.println("Best - Recommended - calculate Total Calories Using Sum : " +
                Optional.of(calculateTotalCaloriesUsingSum()));

        out.println("Good - calculate Total Calories With Method Reference   : " +
                Optional.of(calculateTotalCaloriesWithMethodReference()));
    }

    // -- Better -- Recommended
    private static int calculateTotalCaloriesUsingSum() {
        return Dish.menu.stream()
            .mapToInt(Dish::getCalories)
            .sum();
    }

    // -- Good
    private static int calculateTotalCaloriesWithMethodReference() {
        return Dish.menu.stream()
            .map(Dish::getCalories)
            .reduce(0, Integer::sum);
    }
}