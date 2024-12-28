package dev.lpa;

import java.time.LocalDate;
import java.time.Month;

import static java.lang.System.*;

public class Main {

    public static void main(String[] args) {
        LocalDate today = LocalDate.now();
        out.println(today);

        LocalDate Five5 = LocalDate.of(2022, 5, 5);
        out.println(Five5);

        LocalDate May5th = LocalDate.of(2022, Month.MAY, 5);
        out.println(May5th);

        LocalDate Day125 = LocalDate.ofYearDay(2022, 125);
        out.println(Day125);

        LocalDate May5 = LocalDate.parse("2022-05-05");
        out.println(May5);

        out.println(May5.getYear());
        out.println(May5.getMonth());

        out.println(May5.getMonthValue());

        out.println(May5.getDayOfMonth());
        out.println(May5.getDayOfWeek());
        out.println(May5.getDayOfYear());

        out.println(May5.getYear());
        out.println(May5.getMonth());
        out.println(May5.getDayOfMonth());
        out.println(May5.getDayOfYear());

        out.println(May5.withYear(2000));
        out.println(May5.withMonth(3));
        out.println(May5.withDayOfMonth(4));
        out.println(May5.withDayOfYear(126));
        out.println(May5);
        out.println(May5.withDayOfYear(126));
        out.println(May5.plusYears(1));
        out.println(May5.plusMonths(12));
        out.println(May5.plusDays(365));
        out.println(May5.plusWeeks(52));
        out.println(May5.plusDays(365));

        out.println("May5 > today? " + May5.isAfter(today));
        out.println("today > May5? " + May5.isBefore(today));
        out.println("May5 > today? " + May5.compareTo(today));
        out.println("today > May5? " + today.compareTo(May5));

        out.println("today = now ? " + today.compareTo(LocalDate.now()));
        out.println("today = now ? " + today.equals(LocalDate.now()));

        out.println(today.isLeapYear());
        out.println(May5.minusYears(2).isLeapYear());
    }
}
