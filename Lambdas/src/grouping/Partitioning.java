package grouping;

import static java.lang.System.*;
import static java.util.Comparator.*;
import static java.util.stream.Collectors.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Partitioning implements IPartitioning{

    public static void main(String... args) {
        final Partitioning p = new Partitioning();

        out.println();
        out.println("Dishes partitioned by vegetarian  : " + p.partitionByVegetarian());
        out.println("Vegetarian Dishes by type         : " + p.vegetarianDishesByType());
        out.println("Most caloric dishes by vegetarian : " + p.mostCaloricPartitionedByVegetarian());
        out.println();
    }

    // --------------------------------------------------

    public Map<Boolean, List<Dish>> partitionByVegetarian() {
        return Dish.menu.stream()
            .collect(partitioningBy(Dish::isVegetarian));
    }

    public Map<Boolean, Map<Dish.Type, List<Dish>>> vegetarianDishesByType() {
        return Dish.menu.stream()
            .collect(partitioningBy(Dish::isVegetarian, groupingBy(Dish::getType)));
    }

    public Object mostCaloricPartitionedByVegetarian() {
        return Dish.menu.stream()
            .collect(partitioningBy(Dish::isVegetarian, collectingAndThen(maxBy(comparingInt(Dish::getCalories)), Optional::get)));
    }
}