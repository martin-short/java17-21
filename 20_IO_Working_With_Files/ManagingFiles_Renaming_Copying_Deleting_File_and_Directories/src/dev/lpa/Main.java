package dev.lpa;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.lang.System.*;

public class Main {

    public static void main(String[] args) {
        Path fileDir = Path.of("files");
        Path resourceDir = Path.of("resources");
        try {
            Files.copy(fileDir, resourceDir);
            out.println("Directory copied to " + resourceDir);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
