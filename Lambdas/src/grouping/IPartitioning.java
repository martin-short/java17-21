package grouping;

import java.util.List;
import java.util.Map;

public interface IPartitioning {

    Map<Boolean, List<Dish>> partitionByVegetarian();
    Map<Boolean, Map<Dish.Type, List<Dish>>> vegetarianDishesByType();
    Object mostCaloricPartitionedByVegetarian();
}
