package br.ufrn.imd.multiplicacaomatrizes.utils;

import java.util.Collection;

/**
 * Classe utilitária de asserções
 */
public class AssertionUtils {
    /**
     * Verifica se condição está sendo respeitada
     *
     * @param condition - Condição a ser respeitada
     * @param message   - Mensagem de exceção caso condição não seja respeitada
     */
    public static void makeSure(boolean condition, String message) {
        if (!condition) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * Verifica se objeto é não vazio
     *
     * @param object - Objeto a ser verificado
     * @return Se objeto está vazio
     */
    public static boolean isNotEmpty(Object object) {
        if (object == null) {
            return false;
        }

        if (object instanceof String) {
            return ((String) object).length() > 0;
        }

        if (object instanceof Collection) {
            return ((Collection<?>) object).size() > 0;
        }

        return true;
    }
}
