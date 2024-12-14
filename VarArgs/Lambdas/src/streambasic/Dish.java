package streambasic;

import java.util.Arrays;
import java.util.List;

public class Dish {

    private final String name;
    private final boolean vegetarian;
    private final int calories;

    public enum Type {
        MEAT, FISH, FRUIT, OTHER
    }
    
    public Dish(final String name, final boolean vegetarian, final int calories, Dish.Type type) {
        this.name       = name;
        this.vegetarian = vegetarian;
        this.calories   = calories;
    }

    public String getName() {
        return name;
    }

    public boolean isVegetarian() {
        return vegetarian;
    }

    public int getCalories() {
        return calories;
    }

    @Override
    public String toString() {
        return name;
    }

    protected static final List<Dish> menu = Arrays.asList(
        new Dish("pork",    false, 800, Dish.Type.MEAT),
        new Dish("beef",    false, 700, Dish.Type.MEAT),
        new Dish("chicken", false, 400, Dish.Type.MEAT),
        new Dish("fries",   true,  530, Dish.Type.OTHER),
        new Dish("rice",    true,  350, Dish.Type.OTHER),
        new Dish("berries", true,  120, Dish.Type.FRUIT),
        new Dish("pizza",   true,  550, Dish.Type.OTHER),
        new Dish("prawns",  false, 400, Dish.Type.FISH),
        new Dish("salmon",  false, 450, Dish.Type.FISH)
    );
}
