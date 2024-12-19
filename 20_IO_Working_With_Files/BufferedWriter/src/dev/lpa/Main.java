package dev.lpa;

import dev.lpa.student.Course;
import dev.lpa.student.Student;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

import static java.lang.System.*;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        String header = """
                Student Id,Country Code,Enrolled Year,Age,Gender,\
                Experienced,Course Code,Engagement Month,Engagement Year,\
                Engagement Type""";

        Course jmc  = new Course("JMC", "Java Masterclass");
        Course pymc = new Course("PYC", "Python Masterclass");
        List<Student> students = Stream.generate(() -> Student.getRandomStudent(jmc, pymc))
                .limit(25)
                .toList();

        Path path = Path.of("students.csv");

        try (BufferedWriter writer = Files.newBufferedWriter(Path.of("take2.csv"))) {
            writer.write(header);
            writer.newLine();
            int count = 0;
            for (Student student : students) {
                for (var r : student.getEngagementRecords()) {
                    writer.write(r);
                    writer.newLine();
                    count++;
                    if (count % 5 == 0) {
                        Thread.sleep(100);
                        out.print(".");
                    }
                    if (count % 10 == 0) {
                        writer.flush();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (FileWriter writer = new FileWriter("take3.csv")) {
            writer.write(header);
            writer.write(lineSeparator());
            for (Student student : students) {
                for (var r : student.getEngagementRecords()) {
                    writer.write(r);
                    writer.write(lineSeparator());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (PrintWriter writer = new PrintWriter("take4.txt")) {
            writer.println(header);
            for (Student student : students) {
                for (var r : student.getEngagementRecords()) {
                    String[] recordData = r.split(",");
                    writer.printf("%-12d%-5s%2d%4d%3d%-1s".formatted(
                            student.getStudentId(),  // Student Id
                            student.getCountry(),  // Country Code
                            student.getEnrollmentYear(),  // Enrolled Year
                            student.getEnrollmentMonth(),  // Enrolled Month
                            student.getEnrollmentAge(),  // Age
                            student.getGender()));  // Gender
                    writer.printf("%-1s", (student.hasExperience() ? 'Y' : 'N'));  // Experienced?
                    writer.format("%-3s%10.2f%-10s%-4s%-30s",
                            recordData[7],  // Course Code
                            student.getPercentComplete(recordData[7]),
                            recordData[8],  // Engagement Month
                            recordData[9],  // Engagement Year
                            recordData[10]);  // Engagement Type
                    writer.println();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
