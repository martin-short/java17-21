package grouping;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public interface IGrouping {

    final int MAX_DIET =  400;
    final int MAX_NORM =  700;
    final int MAX_FAT  = 1000;

    Map<Dish.Type, List<Dish>> groupDishesByType();
    Map<Dish.CaloricLevel, List<Dish>> groupDishesByCalories();
    Map<Dish.Type, Map<Dish.CaloricLevel, List<Dish>>> groupDishesByTypeThenByCalories();
    Map<Dish.Type, Long> countDishesInGroups();
    Map<Dish.Type, Optional<Dish>> mostCaloricDishesByType();
    Map<Dish.Type, Dish> mostCaloricDishesByTypeWithoutOptionals();
    Map<Dish.Type, Integer> sumCaloriesByType();
    Map<Dish.Type, Set<Dish.CaloricLevel>> caloriesByType();
}