package grouping;

import static java.lang.System.*;
import static java.util.stream.Collectors.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Grouping implements IGrouping {

    public static void main(final String... args) {
        final Grouping g = new Grouping();

        out.println("m1: grouped by type                  : " + g.groupDishesByType());
        out.println("m2: grouped by calories              : " + g.groupDishesByCalories());
        out.println("m3: grouped by type then by calories : " + g.groupDishesByTypeThenByCalories());
        out.println("m4: Count of groups                  : " + g.countDishesInGroups());
        out.println("m5: Most caloric by type             : " + g.mostCaloricDishesByType());
        out.println("m6: Same but without Optionals       : " + g.mostCaloricDishesByTypeWithoutOptionals());
        out.println("m7: Sum calories by type             : " + g.sumCaloriesByType());
        out.println("m8: Calories by type                 : " + g.caloriesByType());
    }

    // m1: grouped by 'type'
    //        <Key        Value>
    public Map<Dish.Type, List<Dish>> groupDishesByType() {
        return Dish.menu.stream()
            .collect(groupingBy(Dish::getType));
    }

    // m2: grouped by 'calories'
    //        <Key                Value>
    public Map<Dish.CaloricLevel, List<Dish>> groupDishesByCalories() {
        return Dish.menu.stream()
            .collect(groupingBy(dish -> getCaloricLevel(dish.getCalories())));
    }

    // m3: grouped by 'type' then by 'calories'
    //        <Key        Val><Key               Value>
    public Map<Dish.Type, Map<Dish.CaloricLevel, List<Dish>>> groupDishesByTypeThenByCalories() {
        return Dish.menu.stream()
            .collect(groupingBy(Dish::getType, groupingBy((final Dish dish) -> getCaloricLevel(dish.getCalories()))));
    }

    // m4: Count Dishes in groups
    //        <Key        Value>
    public Map<Dish.Type, Long> countDishesInGroups() {
        return Dish.menu.stream()
            .collect(groupingBy(Dish::getType, counting()));
    }

    // m5: Most calories by 'type' with Optionals
    //        <Key        Value>
    public Map<Dish.Type, Optional<Dish>> mostCaloricDishesByType() {
        return Dish.menu.stream()
            .collect(groupingBy(Dish::getType, reducing((final Dish d1, final Dish d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2)));
    }

    // m6: Most calories by 'type' without Optionals
    //        <Key        Value>
    public Map<Dish.Type, Dish> mostCaloricDishesByTypeWithoutOptionals() {
        return Dish.menu.stream()
            .collect(Collectors.toMap(Dish::getType, Function.identity(), (d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2));
    }

    // m7: Sum 'calories' by 'type'
    //        <Key        Value>
    public Map<Dish.Type, Integer> sumCaloriesByType() {
        return Dish.menu.stream()
            .collect(groupingBy(Dish::getType, summingInt(Dish::getCalories)));
    }

    // m8: 'Caloric levels' by 'type'
    //        <Key        Value>
    public Map<Dish.Type, Set<Dish.CaloricLevel>> caloriesByType() {
        return Dish.menu.stream()
            .collect(groupingBy(Dish::getType, mapping(dish -> getCaloricLevel(dish.getCalories()), toSet())));
    }

    private Dish.CaloricLevel getCaloricLevel(int calories) {
        if (calories <= 0) return Dish.CaloricLevel.OTHER;
        if (calories <= MAX_DIET) return Dish.CaloricLevel.DIET;
        if (calories <= MAX_NORM) return Dish.CaloricLevel.NORMAL;
        if (calories < MAX_FAT)   return Dish.CaloricLevel.FAT;
        return Dish.CaloricLevel.OTHER;
    }
}