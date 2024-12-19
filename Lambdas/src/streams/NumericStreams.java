package streams;

import static java.lang.System.*;
import static java.util.stream.Collectors.*;

import java.util.Arrays;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class NumericStreams {

    public static void main(final String... args) {
        final List<Integer> ints = Arrays.asList(
             1,   2,  3,  4,  5,  6,  7,  8,  9, 10,
             11,  12,  13,  14,  15,  16,  17,  18,  19,  20,
             21,  22,  23,  24,  25,  26,  27,  28,  29,  30,
             31,  32,  33,  34,  35,  36,  37,  38,  39,  40,
             41,  42,  43,  44,  45,  46,  47,  48,  49,  50,
             51,  52,  53,  54,  55,  56,  57,  58,  59,  60,
             61,  62,  63,  64,  65,  66,  67,  68,  69,  70,
             71,  72,  73,  74,  75,  76,  77,  78,  79,  80,
             81,  82,  83,  84,  85,  86,  87,  88,  89,  90,
             91,  92,  93,  94,  95,  96,  97,  98,  99, 100,
            101, 102, 103, 104, 105, 106, 107, 108, 109, 110,
            111, 112, 113, 114, 115, 116, 117, 118, 119, 120,
            121, 122, 123, 124
        );

        Arrays.stream(ints.toArray())
            .filter(NumericStreams::isPerfectSquare)
            .map(n -> {
                out.println("\n" + "mapping square: " + n);
                return n;
            })
            .collect(toList());
        out.println();

        final List<Integer> nums = Arrays.asList(3, 4, 5, 1, 2);
        Arrays.stream(nums.toArray()).forEach(out::print);

        out.println("\n\nNumber of calories: .mapToInt(Dish::getCalories).sum(): " +
            Dish.menu.stream().mapToInt(Dish::getCalories).sum());

        out.print("max and OptionalInt: .mapToInt(Dish::getCalories).max(): ");
        final OptionalInt maxCalories =
            Dish.menu.stream().mapToInt(Dish::getCalories).max();

        if (maxCalories.isPresent()) {
            out.println(maxCalories.getAsInt());
        } else {
            out.println(-1);
        }

        out.print("\nnumeric ranges: ");
        final IntStream evens =
            IntStream.rangeClosed(1, 200)
            .filter(n -> n % 2 == 0);
            out.println(evens.count());

        out.println("\nPythagorean Triples");
        final Stream<int[]> triplets =
            IntStream.rangeClosed(1, 10)
            .boxed()
            .flatMap(a ->
                IntStream.rangeClosed(a, 10)
                .filter(b -> Math.sqrt((a * a) + (b * b)) % 1 == 0)
                .boxed()
                .map(b -> new int[] { a, b, (int) Math.sqrt((a * a) + (b * b)) })
            );
        triplets.forEach(t -> out.println(t[0] + ", " + t[1] + ", " + t[2]));
    }

    private static boolean isPerfectSquare(final Object n) {
        return Math.sqrt((int) n) % 1 == 0;
    }
}