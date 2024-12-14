package streams;
import static java.lang.System.*;
import static java.util.stream.Collectors.*;

import java.util.Arrays;

public class Laziness {

    public static void main(final String[] args) {
        final var numbers = Arrays.asList(
             1,  2,  3,  4 , 5,  6,  7,  8,  9, 10,
            11, 12, 13, 14, 15, 16, 17, 18, 19, 20,
            21, 22, 23, 24, 25, 26, 27, 28, 29, 30,
            31, 32, 33, 34, 35, 36, 37, 38, 39, 40
        );

        final var evenSquares = numbers.stream()
            .filter(n -> {
                return n % 2 == 0;   // filter for even numbers
            })
            
            // map() the square of the even numbers
            .map(n -> {
                out.printf("mapping: %d \t squared: %d%n", n, (n * n));
                return (n * n);
            })
            .limit(40)
            .collect(toList());

        out.printf("%nEven numbers squared: %s", evenSquares);
    }
}