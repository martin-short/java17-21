package streams;

import java.util.Arrays;
import java.util.List;

public class Dish {

    private final String name;
    private final boolean vegetarian;
    private final int calories;
    private final Type type;

    public enum Type {
        MEAT, FISH, VEGETABLE, STARCH, FRUIT, OTHER
    }

    protected static final List<Dish> menu = Arrays.asList(
        new Dish("pork",   false, 800, Dish.Type.MEAT),
        new Dish("fries",  true,  530, Dish.Type.STARCH),
        new Dish("rice",   true,   35, Dish.Type.STARCH),
        new Dish("fruit",  true,  120, Dish.Type.FRUIT),
        new Dish("pizza",  false, 550, Dish.Type.OTHER),
        new Dish("prawns", false, 400, Dish.Type.FISH),
        new Dish("salmon", false, 450, Dish.Type.FISH)
    );

    public Dish(final String name, final boolean vegetarian, final int calories, Dish.Type type) {
        this.name = name;
        this.vegetarian = vegetarian;
        this.calories = calories;
        this.type = type;
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

    public Type getType() {
        return type;
    }

    @Override
    public String toString() {
        return name;
    }
}