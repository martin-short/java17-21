package dev.lpa;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.lang.System.*;

public class Main {

    public static void main(String[] args) {
        useFile("testfile.txt");
        usePath("pathfile.txt");
    }

    private static void useFile(String fileName) {
        File file = new File(fileName);
        boolean fileExists = file.exists();

        out.printf("File '%s' %s%n", fileName, fileExists ? "exists." : "does not exist.");
        if (fileExists) {
            out.println("Deleting File: " + fileName);
            fileExists = !file.delete();
        }

        if (!fileExists) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                out.println("Something went wrong");
            }
            out.println("Created File: " + fileName);
            if (file.canWrite()) {
                out.println("Would write to file here");
            }
        }
    }

    private static void usePath(String fileName) {
        Path path = Path.of(fileName);
        boolean fileExists = Files.exists(path);

        out.printf("File '%s' %s%n", fileName, fileExists ? "exists." : "does not exist.");
        if (fileExists) {
            out.println("Deleting File: " + fileName);
            try {
                Files.delete(path);
                fileExists = false;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (!fileExists) {
            try {
                Files.createFile(path);
                out.println("Created File: " + fileName);
                if (Files.isWritable(path)) {
                    Files.writeString(path, """
                            Here is some data,
                            For my file,
                            just to prove,
                            Using the Files class and path are better!
                            """);
                }
                out.println("And I can read too");
                out.println("-------------------------");
                Files.readAllLines(path).forEach(out::println);
            } catch (IOException e) {
                out.println("Something went wrong");
            }
        }
    }
}
