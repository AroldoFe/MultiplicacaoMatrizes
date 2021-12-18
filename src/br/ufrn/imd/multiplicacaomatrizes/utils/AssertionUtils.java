package br.ufrn.imd.multiplicacaomatrizes.utils;

public class AssertionUtils {
    public static void makeSure(boolean condition, String message) {
        if (!condition) {
            throw new IllegalArgumentException(message);
        }
    }
}
