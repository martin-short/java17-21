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

        htmlMatcher.reset();
        htmlMatcher.results().forEach(mr -> out.println(mr.group(1) + " " + mr.group(2)));

        String tabbedText = """
                group1	group2	group3
                1	2	3
                a	b	d
                """;

        tabbedText.lines()
                .flatMap(s -> Pattern.compile("\\t").splitAsStream(s))
                .forEach(out::println);

        htmlMatcher.reset();
        String updatedSnippet = htmlMatcher.replaceFirst(mr -> "<em>" + mr.group(2) + "</em>");
        out.println("------------------");
        out.println(updatedSnippet);
        out.println(htmlMatcher.start() + " : " + htmlMatcher.end());
        out.println(htmlMatcher.group(2));

        htmlMatcher.usePattern(Pattern.compile("<([hH]\\d)>(.*)</\\1>"));

        htmlMatcher.reset();
        out.println("------------------");
        out.println("Using Back Reference: \n" +
                htmlMatcher.replaceFirst("<em>$2</em>"));

        String replacedHTML = htmlMatcher.replaceAll(mr -> "<em>" + mr.group(2) + "</em>");
        out.println("------------------");
        out.println(replacedHTML);

        htmlMatcher.reset();
        StringBuilder sb = new StringBuilder();
        int index = 1;
        while (htmlMatcher.find()) {
            htmlMatcher.appendReplacement(sb,
                    switch (htmlMatcher.group(1).toLowerCase()) {
                        case "h1" -> "<head>$2</head>";
                        case "h2" -> "<em>$2</em>";
                        default -> "<$1>" + index++ + ". $2</$1>";
                    });
        }
        htmlMatcher.appendTail(sb);
        out.println(sb);
    }
}
