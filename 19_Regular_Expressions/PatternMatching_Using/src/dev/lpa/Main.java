package dev.lpa;

import java.util.regex.Pattern;

import static java.lang.System.*;

public class Main {

    public static void main(String[] args) {
        String sentence = "I like B.M.W. motorcycles.";
        boolean matched = Pattern.matches("[A-Z].*[.]", sentence);
        out.println(matched + ": " + sentence);

        Pattern firstPattern = Pattern.compile("[A-Z].*?[.]");
        var matcher = firstPattern.matcher(sentence);
        out.println(matcher.matches() + ": " + sentence);
        out.println("sentence.length: " + sentence.length());
        out.println("Matched Ending Index: " + matcher.end());

        out.println(matcher.lookingAt() + ": " + sentence);
        out.println("Matched Ending Index: " + matcher.end());
        out.println("Matched on : " + sentence.substring(0, matcher.end()));

        matcher.reset();
        out.println(matcher.find() + ": " + sentence);
        out.println("Matched Ending Index: " + matcher.end());
        out.println("Matched on : " + sentence.substring(matcher.start(), matcher.end()));
    }
}
