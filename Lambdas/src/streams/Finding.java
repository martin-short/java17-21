package streams;

import static java.lang.System.*;

import java.util.Optional;

public class Finding {

    public static void main(final String... args) {
        Finding finding = new Finding();

        if (finding.isVegiFriendlyMenu()) {
            out.println("\n\nVegetarian friendly");
        } else {
            out.println("\n\nNOT Vegetarian friendly");
        }

        if (finding.isHealthyMenu()) {
            out.println(finding.isHealthyMenu());
        } else {
            out.println("\n\nNOT Healthy friendly(1)");
        }

        if (finding.isHealthyMenu2()) {
            out.println(finding.isHealthyMenu2());
        } else {
            out.println("\n\nNOT Healthy friendly(2)");
        }

        final Optional<Dish> dish = finding.findVegiDish();
        out.print("\n\ndish.ifPresent() -> ");
        dish.ifPresent(d -> out.println(d.getName()));
    }

    // ----------------------------------------------------- //

    private boolean isVegiFriendlyMenu() {
        out.print("\n\nisVegiFriendlyMenu(): dish.anyMatch() -> ");
        return Dish.menu.stream()
            .anyMatch(Dish::isVegetarian);
    }

    private boolean isHealthyMenu() {
        out.print("\n\nisHealthyMenu(): dish.allMatch() -> ");
        return Dish.menu.stream()
            .allMatch(d -> d.getCalories() < 500);
    }

    private boolean isHealthyMenu2() {
        out.print("\n\nisHealthyMenu2(): dish.noneMatch() -> ");
        return Dish.menu.stream()
            .noneMatch(d -> d.getCalories() >= 500);
    }

    private Optional<Dish> findVegiDish() {
        out.print("\n\nfindVegiDish(): dish.findAny() -> ");
        return Dish.menu.stream()
            .filter(Dish::isVegetarian)
            .findAny();
    }
}
