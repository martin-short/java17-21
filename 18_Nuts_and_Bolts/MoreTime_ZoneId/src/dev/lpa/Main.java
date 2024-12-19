package dev.lpa;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashSet;
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
    }
}
