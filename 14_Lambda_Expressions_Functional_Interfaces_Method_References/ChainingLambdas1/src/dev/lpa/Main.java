package dev.lpa;

import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Main {

    public static void main(String[] args) {
        final String fname = "Chuck";
        final String lname = "Martin";

        Function<String, String> uCase = String::toUpperCase;
        System.out.println(uCase.apply(fname));

        Function<String, String> lastName = s -> s.concat(" " + lname);
        Function<String, String> uCaseLastName = uCase.andThen(lastName);
        System.out.println(uCaseLastName.apply(fname));

        uCaseLastName = uCase.compose(lastName);
        System.out.println(uCaseLastName.apply(fname));

        Function<String, String[]> f0 = uCase
                .andThen(s -> s.concat(" " + lname))
                .andThen(s -> s.split(" "));
        System.out.println(Arrays.toString(f0.apply(fname)));

        Function<String, String> f1 = uCase
                .andThen(s -> s.concat(" " + lname))
                .andThen(s -> s.split(" "))
                .andThen(s -> s[1].toUpperCase() + ", " + s[0]);
        System.out.println(f1.apply(fname));

        Function<String,Integer> f2 = uCase
                .andThen(s -> s.concat(" " + lname))
                .andThen(s -> s.split(" "))
                .andThen(s -> String.join(", ", s))
                .andThen(String::length);
        System.out.println(f2.apply(fname));

        String[] names = {"Ann", "Bob", "Carol"};
        Consumer<String> s0 = s -> System.out.print(s.charAt(0));
        Consumer<String> s1 = System.out::println;
        Arrays.asList(names).forEach(s0
                .andThen(s-> System.out.print(" - "))
                .andThen(s1));

        Predicate<String> p1 = s -> s.equals("TIM");
        Predicate<String> p2 = s -> s.equalsIgnoreCase("Chuck");
        Predicate<String> p3 = s -> s.startsWith("T");
        Predicate<String> p4 = s -> s.endsWith("e");

        Predicate<String> combined1 = p1.or(p2);
        System.out.println("combined1 = " + combined1.test(fname));

        Predicate<String> combined2 = p3.and(p4);
        System.out.println("combined2 = " + combined2.test(fname));

        Predicate<String> combined3 = p3.and(p4).negate();
        System.out.println("combined3 = " + combined3.test(fname));

    }
}
