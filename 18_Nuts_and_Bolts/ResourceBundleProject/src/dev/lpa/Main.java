package dev.lpa;

import java.util.ResourceBundle;

import static java.lang.System.*;

public class Main {

    public static void main(String[] args) {
        ResourceBundle rb = ResourceBundle.getBundle("BasicText");
        out.println(rb.getClass().getName());
        out.println(rb.getBaseBundleName());
        out.println(rb.keySet());

        out.printf("%s %s!%n", rb.getString("hello"), rb.getString("world"));
    }
}
