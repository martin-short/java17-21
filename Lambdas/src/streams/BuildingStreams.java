package streams;

import static java.lang.System.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileTime;
import java.nio.file.attribute.UserPrincipal;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class BuildingStreams {

    static Logger logger = Logger.getLogger(BuildingStreams.class.getName());

    private static final String STR = "A bcd E fgh I jklmn O pqrst U vwxyz";

    private static final int[] ODDS = {
         1,  3,  5,  7,  9, 11, 13, 15, 17, 19, 21, 23, 25, 27,
        29, 31, 33, 35, 37, 39, 41, 43, 45, 47, 49, 51, 53, 55,
        57, 59, 61, 63, 65, 67, 69, 71, 73, 75, 77, 79, 81, 83,
        85, 87, 89, 91, 93, 95, 97, 99
    };

    private static final String FNAME1 = "data1.txt";
    private static final String FNAME2 = "data3.txt";
    private static final String ISEMPTY  = "Stream is Empty";
    private static final String NOTEMPTY = "Stream is --NOT-- Empty";

    public static void main(final String... args) {
        logger.setLevel(Level.INFO);

        // Set the Stream to empty
        if (emptyStream()) {
            out.printf("%s%n", ISEMPTY);
        } else {
            out.printf("%s%n", NOTEMPTY);
        }

        // Convert Stream to UPPER/lower case
        out.println();
        convertStreamToUpperCase().forEach(out::print);
        out.println();
        convertStreamToLowerCase().forEach(out::print);
        out.println();

        // Sum the values of a numeric stream
        out.printf("%d%n", sumStreamNums());

        // Iterate Stream with limit
        iterateStreamWithLimit(5);
        iterateStreamWithLimit(10);
        iterateStreamWithLimit(15);
        iterateStreamWithLimit(20);
        iterateStreamWithLimit(25);

        // Generate numeric Streams
        generateRandomStreamOfDoubles();
        generateStreamOfOnesWithIntStreamGenerate();
        generateStreamOfTwosWithIntStreamGenerate();

        // Get the file status (exists, owner, modified, compared to, same as)
        findFileStats(FNAME1, FNAME2);

        // Get the unique words (and count of) in a file
        findUniqueWords(FNAME1);
        findUniqueWords(FNAME2);
        findCountOfUniqueWords(FNAME1);
        findCountOfUniqueWords(FNAME2);

        out.println();
    }

    // ----------------------------------------- //

    private static boolean emptyStream() {
        out.printf("%n%s", "Stream.empty:");
        Stream<String> s = Stream.empty();
        return (s.findAny().isEmpty());
    }

    private static Stream<String> convertStreamToUpperCase() {
        final Stream<String> s = Stream.of(STR);
        return s.map(String::toUpperCase);
    }

    private static Stream<String> convertStreamToLowerCase() {
        final Stream<String> s = Stream.of(STR);
        return s.map(String::toLowerCase);
    }

    private static int sumStreamNums() {
        out.printf("%n%s", "Sum of the odd numbers from 1 through 69: ");
        return Arrays.stream(ODDS).sum();
    }

    private static void iterateStreamWithLimit(int i) {
        out.printf("%n%n%s%d%s", "Stream.iterate - limit ", i, " - (even numbers): ");
        Stream.iterate(0, n -> n + 2)
            .limit(i)
            .forEach(s -> out.print(s + ", "));
    }

    private static void generateRandomStreamOfDoubles() {
        out.printf("%n%n%s", "Random stream of doubles with Stream.generate");
        Stream.generate(Math::random)
            .limit(5)
            .forEach(out::println);
    }

    private static void generateStreamOfOnesWithIntStreamGenerate() {
        out.printf("%n%n%s", "stream of 1's with IntStream.generate: ");
        IntStream.generate(() -> 1)
            .limit(15)
            .forEach(out::print);
    }

    private static void generateStreamOfTwosWithIntStreamGenerate() {
        out.printf("%n%s", "stream of 2's with IntStream.generate(new IntSupplier(): ");
        IntStream.generate(() -> 2)
            .limit(15)
            .forEach(out::print);
    }

    private static void findUniqueWords(final String s) {
        out.printf("%n%n%s", "Find unique words: ");
        final Path path = Paths.get(s);
        try (Stream<String> lines = Files.lines(path)) {
            lines.flatMap(line -> Arrays.stream(line.split(" ")))
                 .distinct()
                 .forEach(ss -> out.print(ss + ", "));
        } catch (final IOException ex) {
            logger.log(Level.WARNING, ex.toString());
        }
        out.println();
    }

    private static void findCountOfUniqueWords(final String s) {
        out.printf("%s%s:", "Find count of unique words for: ", s);
        final Path path = Paths.get(s);
        try (Stream<String> lines = Files.lines(path)) {
            final long wc = lines.flatMap(line -> Arrays.stream(line.split(" ")))
                .distinct()
                .count();
            out.println(wc);
        } catch (final IOException ex) {
            logger.log(Level.WARNING, ex.toString());
        }
    }

    private static void findFileStats(final String file1, final String file2) {
        out.printf("%n%n%s%n%s", "File I/O", "--------");

        if (!fileExists(file1)) {
            out.printf("%n%s DOES NOT EXIST: ", file1);
        } else {
            try {
                out.printf("%n%s EXISTS: ", file1);
                out.printf("%n%s OWNER: ", fileOwner(file1));
                out.printf("%n MODIFIED: %s", fileLastModified(file1));
                out.printf("%n%s COMPARED TO: %s: %s", FNAME1, FNAME2, filesCompare(file1, file2));
                out.printf("%n%s SAME AS: %s: %s", FNAME1, FNAME2, fileSameAs(file1, file2));
            } catch (final IOException ex) {
                logger.log(Level.WARNING, ex.toString());
            }
        }
        out.printf("%n%s%n", "--------");
    }

    private static boolean fileExists(final String s) {
        final Path p = Path.of(s);
        return Files.exists(p);
    }

    private static UserPrincipal fileOwner(final String s) throws IOException {
        final Path path = Path.of(s);
        return Files.getOwner(path);
    }

    private static FileTime fileLastModified(final String s) throws IOException {
        return Files.getLastModifiedTime(Path.of(s));
    }

    private static long filesCompare(final String s1, final String s2) throws IOException {
        return (Files.mismatch(Path.of(s1), Path.of(s2)) != -1 ? 1L : -1L);
    }

    private static String fileSameAs(final String s1, final String s2) throws IOException {
        return (Files.isSameFile(Path.of(s1), Path.of(s2)) ? "the same file" : "NOT the same file");
    }
}
