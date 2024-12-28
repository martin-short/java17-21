package dev.lpa;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>(List.of(
                "alpha", "bravo", "charlie", "delta"));

        for (String s : list) {
            System.out.println(s);
        }

        System.out.println("-------");
        list.forEach(System.out::println);

        System.out.println("-------");
        String prefix = "nato";
        list.forEach((var myString) -> {
            char first = myString.charAt(0);
            System.out.println(prefix + " " + myString + " means " + first);
        });

        var result = calculator(Integer::sum,5, 2);
        System.out.println("result " + result);

        var result2 = calculator((a, b) -> a / b, 10.0, 2.5);
        System.out.println("result2 " + result2);

        var result3 = calculator((a, b) -> a.toUpperCase() + " " + b.toUpperCase(),
                "Ralph", "Kramden");
        System.out.println("result3 " + result3);
    }

    public static <T> T calculator(Operation<T> function, T value1, T value2) {
        return function.operate(value1, value2);
    }
}
