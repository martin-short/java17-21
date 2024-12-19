package dev.lpa;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static java.lang.System.*;

public class Main {

    public static void main(String[] args) {
        try (FileReader reader = new FileReader("file.txt")) {
            char[] block = new char[1000];
            int data;
            while ((data = reader.read(block)) != -1) {
                String content = new String(block, 0, data);
                out.printf("---> [%d chars] %s%n", data, content);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        out.println("-----------------------------------");
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("file.txt")))
        {
            bufferedReader.lines().forEach(out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
