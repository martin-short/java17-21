package lambdas;

import static java.lang.System.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

interface IApplePredicate {
    public boolean test(Apple a);
}

public class Lambdas {

    private static final String GREEN  = "green";
    private static final String RED    = "red";
    private static final String GOLDEN = "golden";
    private static final String FMT    = "%n%s";

    public static void main(final String... args) {
        // Simple example
        final Runnable r = () -> out.printf("%n%s", "\nHello!");
        r.run();

        out.printf("%s", "------------------------");

        // Filtering with lambdas
        final List<Apple> apples = Arrays.asList(
            new Apple( 80, GREEN),
            new Apple(155, GREEN),
            new Apple(120, RED),
            new Apple(150, RED),
            new Apple(130, GOLDEN),
            new Apple(160, GOLDEN)
        );

        final List<Apple> greens = appleFilter(apples, (final Apple a) ->
            GREEN.equals(a.getColor()));
            out.printf(FMT, greens);

        final List<Apple> reds = appleFilter(apples, (final Apple a) ->
            RED.equals(a.getColor()));
            out.printf(FMT, reds);

        final List<Apple> goldens = appleFilter(apples, (final Apple a) ->
            GOLDEN.equals(a.getColor()));
            out.printf(FMT, goldens);

        final Comparator<Apple> c = (final Apple a1, final Apple a2) ->
            a1.getWeight().compareTo(a2.getWeight());

        apples.sort(c);
        out.printf(FMT, apples);
    }

    public static List<Apple> appleFilter(final List<Apple> apples, final IApplePredicate p) {
        final List<Apple> result = new ArrayList<>();
        for (final Apple a : apples) {
            if (p.test(a)) result.add(a);
        }
        return result;
    }
}
