package br.ufrn.imd.multiplicacaomatrizes.matrizes;

import br.ufrn.imd.multiplicacaomatrizes.utils.AssertionUtils;

public class Dimensao {
    private final Integer linhas;
    private final Integer colunas;

    private Dimensao(Integer linhas, Integer colunas) {
        AssertionUtils.makeSure(linhas != null && linhas.compareTo(0) > 0, "Quantidade de linhas tem que ser maior que 0");
        AssertionUtils.makeSure(colunas != null && !colunas.equals(0), "Quantidade de linhas tem que ser maior que 0");

        this.linhas = linhas;
        this.colunas = colunas;
    }

    public static Dimensao of(Integer linhas, Integer colunas) {
        return new Dimensao(linhas, colunas);
    }

    public Integer getLinhas() {
        return linhas;
    }

    public Integer getColunas() {
        return colunas;
    }

    public String toString(String delimitador) {
        return linhas + delimitador + colunas;
    }
}
