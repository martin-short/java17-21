package lambdas;

import org.jetbrains.annotations.NotNull;

import java.util.Comparator;

public class AppleComparator implements Comparator<Apple> {

    public int compare(final @NotNull Apple a1, final @NotNull Apple a2) {
        return a1.getWeight().compareTo(a2.getWeight());
    }
}
