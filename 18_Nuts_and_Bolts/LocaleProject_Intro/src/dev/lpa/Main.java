package dev.lpa;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;
import java.util.Locale;

import static java.lang.System.*;

public class Main {

    public static void main(String[] args) {

        Locale.setDefault(Locale.US);
        out.println("Default Locale = " + Locale.getDefault());

        Locale en   = new Locale("en");
        Locale enAU = new Locale("en", "AU");
        Locale enCA = new Locale("en", "CA");

        Locale enIN = new Locale.Builder().setLanguage("en").setRegion("IN").build();
        Locale enNZ = new Locale.Builder().setLanguage("en").setRegion("NZ").build();

        var dtf = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);

        for (var locale : List.of(Locale.getDefault(), Locale.US, en, enAU, enCA,
                Locale.UK, enNZ, enIN))
        {
            out.println(locale.getDisplayName() + "= "
                    + LocalDateTime.now().format(dtf.withLocale(locale)));
        }
    }
}
