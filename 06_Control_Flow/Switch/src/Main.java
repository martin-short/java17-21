import static java.lang.System.*;

public class Main {

    public static void main(String[] args) {
        int switchValue = 3;
        String month = "APRIL";

        switch (switchValue) {
            case 1 -> out.println("Value was 1");
            case 2 -> out.println("Value was 2");
            case 3, 4, 5 -> {
                out.println("It was either a 3, 4 or 5");
                out.println("It was a " + switchValue);
            }
            default -> out.println("It was not 1, 2, 3, 4 or 5");
        }

        out.println(month + " is in the " + getQuarter(month) + " quarter");
    }

    public static String getQuarter(String month) {
        return switch (month) {
            case "JANUARY", "FEBRUARY", "MARCH" -> "1st";
            case "APRIL", "MAY", "JUNE" -> "2nd";
            case "JULY", "AUGUST", "SEPTEMBER" -> "3rd";
            case "OCTOBER", "NOVEMBER", "DECEMBER" -> "4th";
            default -> month + " is bad";
        };
    }
}
