package dev.lpa;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.TemporalAdjusters;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;

import static java.lang.System.*;

public class Main {

    public static void main(String[] args) {
        setProperty("user.timezone", "America/Los_Angeles");
        out.println(ZoneId.systemDefault());

        out.println("Number of TZs = " + ZoneId.getAvailableZoneIds().size());
        ZoneId.getAvailableZoneIds().stream()
                .filter(s -> s.startsWith("US"))
                .sorted()
                .map(ZoneId::of)
                .forEach(z -> out.println(z.getId() + ": " + z.getRules()));

        Set<String> jdk8Zones = ZoneId.getAvailableZoneIds();
        String[] alternate = TimeZone.getAvailableIDs();
        Set<String> oldway = new HashSet<>(Set.of(alternate));

        oldway.removeAll(jdk8Zones);
        out.println(oldway);
        ZoneId bet = ZoneId.of("BET", ZoneId.SHORT_IDS);
        out.println(bet);

        LocalDateTime now = LocalDateTime.now();
        out.println(now);

        Instant instantNow = Instant.now();
        out.println(instantNow);

        for (ZoneId z : List.of(ZoneId.of("Australia/Sydney"),
                                ZoneId.of("Europe/Paris"),
                                ZoneId.of("America/New_York")))
        {
            DateTimeFormatter zoneFormat = DateTimeFormatter.ofPattern("z:zzzz");
            out.println(z);
            out.println("\t" + instantNow.atZone(z).format(zoneFormat));
            out.println("\t" + z.getRules().getDaylightSavings(instantNow));
            out.println("\t" + z.getRules().isDaylightSavings(instantNow));
        }

        Instant dobInstant = Instant.parse("2020-01-01T08:01:00Z");
        LocalDateTime dob = LocalDateTime.ofInstant(dobInstant, ZoneId.systemDefault());
        out.println("Your kid's birthdate, LA time = " + dob.format(
                DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)));

        ZonedDateTime dobSydney = ZonedDateTime.ofInstant(dobInstant,
                ZoneId.of("Australia/Sydney"));
        out.println("Your kid's birthdate, Sydney Time = " + dobSydney.format(
                DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)));

        ZonedDateTime dobHere = dobSydney.withZoneSameInstant(ZoneId.systemDefault());
        out.println("Your kid's birthdate, Here Time = " + dobHere.format(
                DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)));

        ZonedDateTime firstOfMonth = ZonedDateTime.now()
                .with(TemporalAdjusters.firstDayOfNextMonth());
        out.printf("First of next Month = %tD %n", firstOfMonth);

        Period timePast = Period.between(LocalDate.EPOCH, dob.toLocalDate());
        out.println(timePast);

        Duration timeSince = Duration.between(Instant.EPOCH, dob.toInstant(ZoneOffset.UTC));
        out.println(timeSince);

        LocalDateTime dob2 = dob.plusYears(2).plusMonths(4)
                .plusDays(4).plusHours(7).plusMinutes(14).plusSeconds(37);

        out.println("Your 2nd kid's birthdate, Here Time = " + dob2.format(
                DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)));

        Period timePast2 = Period.between(LocalDate.EPOCH, dob2.toLocalDate());
        out.println(timePast2);

        Duration timeSince2 = Duration.between(Instant.EPOCH, dob2.toInstant(ZoneOffset.UTC));
        out.println(timeSince2);
    }
}
