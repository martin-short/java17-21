package dev.lpa;

public class Main {

    public static void main(String[] args) {
        Integer boxedInt = Integer.valueOf(15);      // preferred but unnecessary
        int unboxedInt = boxedInt.intValue();        // unnecessary

        // Automatic
        Integer autoBoxed = 15;
        int autoUnboxed = autoBoxed;
        System.out.println(autoBoxed.getClass().getName());

        Double resultBoxed = getLiteralDoublePrimitive();
        double resultUnboxed = getDoubleObject();
    }

    private static Double getDoubleObject() {
        return Double.valueOf(100.00);
    }

    private static double getLiteralDoublePrimitive() {
        return 100.0;
    }
}