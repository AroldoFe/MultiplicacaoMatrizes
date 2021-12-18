package br.ufrn.imd.multiplicacaomatrizes.matrizes;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DimensaoTest {

    @Test(expected = IllegalArgumentException.class)
    public void of_deveLancarExceptionLinhasNulas() {
        Dimensao.of(null, 5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void of_deveLancarExceptionLinhasIgual0() {
        Dimensao.of(0, 5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void of_deveLancarExceptionLinhasMenorQue0() {
        Dimensao.of(-5, 5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void of_deveLancarExceptionColunasNulas() {
        Dimensao.of(5, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void of_deveLancarExceptionColunasIgual0() {
        Dimensao.of(5, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void of_deveLancarExceptionColunasMenorQue0() {
        Dimensao.of(5, -1);
    }

    @Test
    public void of_deveRetornarDimensaoValida() {
        final var dimensao = Dimensao.of(5, 6);

        assertEquals(Integer.valueOf(5), dimensao.getLinhas());
        assertEquals(Integer.valueOf(6), dimensao.getColunas());

        assertEquals("5x6", dimensao.toString("x"));
    }
}
