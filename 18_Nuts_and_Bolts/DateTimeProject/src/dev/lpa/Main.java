package dev.lpa;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoField;

import static java.lang.System.*;

public class Main {

    public static void main(String[] args) {
        LocalDate today = LocalDate.now();
        out.println(today);

        LocalDate five = LocalDate.of(2024, 5, 5);
        out.println(five);

        LocalDate may5 = LocalDate.of(2024, Month.MAY, 5);
        out.println(may5);

        LocalDate day125 = LocalDate.ofYearDay(2024, 125);
        out.println(day125);

        LocalDate May5 = LocalDate.parse("2024-05-05");
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

        out.println("-------------------");
        May5.datesUntil(May5.plusDays(7))
                .forEach(out::println);

        out.println("-------------------");
        May5.datesUntil(May5.plusYears(1), Period.ofDays(7))
                .forEach(out::println);

        LocalTime time = LocalTime.now();
        out.println(time);

        LocalTime sevenAM = LocalTime.of(7, 0);
        out.println(sevenAM);

        LocalTime sevenThirty = LocalTime.of(7, 30, 15);
        out.println(sevenThirty);

        LocalTime sevenPM = LocalTime.parse("19:00");
        LocalTime sevenThirtyPM = LocalTime.parse("19:30:15.1000");
        out.println(sevenPM.get(ChronoField.AMPM_OF_DAY));
        out.println(sevenThirtyPM.get(ChronoField.AMPM_OF_DAY));

        out.println(sevenThirtyPM.getHour());
        out.println(sevenThirtyPM.getHour());

        out.println(sevenThirtyPM.plusHours(24));

        out.println(sevenPM.range(ChronoField.HOUR_OF_DAY));
        out.println(sevenPM.range(ChronoField.MINUTE_OF_HOUR));
        out.println(sevenPM.range(ChronoField.MINUTE_OF_DAY));
        out.println(sevenPM.range(ChronoField.SECOND_OF_MINUTE));
        out.println(sevenPM.range(ChronoField.SECOND_OF_DAY));

        LocalDateTime todayAndNow = LocalDateTime.now();
        out.println(todayAndNow);

        LocalDateTime May5Noon = LocalDateTime.of(2024, 5, 5, 12, 0);
        out.printf("%tD %tr %n", May5Noon, May5Noon);
        out.printf("%1$tF %1$tT %n", May5Noon);

        out.println(todayAndNow.format(DateTimeFormatter.ISO_WEEK_DATE));

        DateTimeFormatter dtf = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL);
        out.println(May5Noon.format(dtf));

        out.println(May5Noon.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)));

        LocalDateTime May6Noon = May5Noon.plusHours(24);
        out.println(May6Noon.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)));
    }
}
