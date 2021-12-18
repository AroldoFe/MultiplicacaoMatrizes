package br.ufrn.imd.multiplicacaomatrizes.utils;

public class AssertionUtils {
    public static void makeSure(boolean condition, String message) {
        if (!condition) {
            throw new IllegalArgumentException(message);
        }
    }

    public static boolean isNotEmpty(Object object) {
        if (object == null) {
            return false;
        }

        if (object instanceof String) {
            return ((String) object).length() > 0;
        }

        return true;
    }
}
