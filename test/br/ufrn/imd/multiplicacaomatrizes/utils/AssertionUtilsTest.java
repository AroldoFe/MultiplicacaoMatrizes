package br.ufrn.imd.multiplicacaomatrizes.utils;

import org.junit.Test;

import java.util.Collections;

import static org.junit.Assert.assertFalse;

public class AssertionUtilsTest {

    @Test(expected = IllegalArgumentException.class)
    public void makeSure_deveEstourarException() {
        AssertionUtils.makeSure(false, "Mensagem de exceção");
    }

    @Test
    public void makeSure_NaoDeveEstourarException() {
        AssertionUtils.makeSure(true, "Mensagem de exceção");
    }

    @Test
    public void isNotEmpty_DeveIdentificarObjetoNulo() {
        assertFalse(AssertionUtils.isNotEmpty(null));
    }

    @Test
    public void isNotEmpty_DeveIdentificarStringVazia() {
        assertFalse(AssertionUtils.isNotEmpty(""));
    }

    @Test
    public void isNotEmpty_DeveIdentificarColecaoVazia() {
        assertFalse(AssertionUtils.isNotEmpty(Collections.emptyList()));
    }

    @Test
    public void isNotEmpty_DeveIdentificarMatrizVazia() {
        assertFalse(AssertionUtils.isNotEmpty(new Integer[0]));

        assertFalse(AssertionUtils.isNotEmpty(new Integer[0][0]));
    }
}
