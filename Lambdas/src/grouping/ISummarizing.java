package grouping;

import java.util.IntSummaryStatistics;

public interface ISummarizing {
    
    long howManyDishes();
    Dish getMostCaloricDish();
    Dish getMostCaloricDishUsingComparator();
    int calculateTotalCalories();
    Double calculateAverageCalories();
    IntSummaryStatistics calculateMenuStatistics();
    String getShortMenu();
    String getShortMenuCommaSeparated();
}