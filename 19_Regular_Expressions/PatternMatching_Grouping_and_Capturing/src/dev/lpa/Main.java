package dev.lpa;

import java.util.regex.Matcher;
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
        out.println("Matched on : " + matcher.group());

        String htmlSnippet = """
                <H1>My Heading</H1>
                <h2>Sub-heading</h2>
                <p>This is a paragraph about something.</p>
                <p>This is another paragraph about something else.</p>
                <h3>Summary</h3>
                """;

        Pattern htmlPattern = Pattern.compile("<[hH](?<level>\\d)>(.*)</[hH]\\d>");
        Matcher htmlMatcher = htmlPattern.matcher(htmlSnippet);

        while (htmlMatcher.find()) {
            out.println(htmlMatcher.group("level") + " " + htmlMatcher.group(2));
            out.println("index = " + htmlMatcher.start("level"));
        }
    }
}
