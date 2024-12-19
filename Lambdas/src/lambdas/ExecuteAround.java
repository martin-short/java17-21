package lambdas;

import static java.lang.System.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

interface IBufReader {
    public String process(BufferedReader b) throws IOException;
}

public class ExecuteAround {
    
    public static void main(final String... args) throws IOException {

        // Two Lines, Four Lines
        final String twoLines = "\n" +
            procFile((final BufferedReader b) ->
                b.readLine() + "\n" +
                b.readLine()
            );

        final String fourLines = "\n" +
            procFile((final BufferedReader b) ->
                b.readLine() + "\n" +
                b.readLine() + "\n" +
                b.readLine() + "\n" +
                b.readLine()
            );

        // Print the lines
        out.printf("%s", twoLines);
        out.printf("%s", fourLines);
    }

    public static String procFile(final IBufReader p) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("data1.txt"))) {
            return p.process(br);
        }
    }
}
