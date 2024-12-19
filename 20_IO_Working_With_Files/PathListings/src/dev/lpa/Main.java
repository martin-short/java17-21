package dev.lpa;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.Instant;

import static java.lang.System.*;

public class Main {

    public static void main(String[] args) {
        Path path = Path.of("this/is/several/folders/testing.txt");
        logStatement(path);
        extraInfo(path);
    }

    private static void printPathInfo(Path path) {
        out.println("Path: " + path);
        out.println("fileName = " + path.getFileName());
        out.println("parent = " + path.getParent());
        Path absolutePath = path.toAbsolutePath();
        out.println("Absolute Path: = " + absolutePath);
        out.println("Absolute Path Root: = " + absolutePath.getRoot());
        out.println("Root = " + path.getRoot());
        out.println("isAbsolute = " + path.isAbsolute());
        out.println(absolutePath.getRoot());

        int pathParts = absolutePath.getNameCount();
        for (int i = 0; i < pathParts; i++) {
            out.println(".".repeat(i + 1) + " " + absolutePath.getName(i));
        }
        out.println("-----------------------");
    }

    private static void logStatement(Path path) {
        try {
            Path parent = path.getParent();
            if (!Files.exists(parent)) {
                Files.createDirectories(parent);
            }
            Files.writeString(path, Instant.now() + ": hello file world\n",
                    StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void extraInfo(Path path) {
        try {
            var atts = Files.readAttributes(path, "*");
            atts.entrySet().forEach(out::println);
            out.println(Files.probeContentType(path));
        } catch (IOException e) {
            out.println("Problem getting attributes");
        }
    }
}
