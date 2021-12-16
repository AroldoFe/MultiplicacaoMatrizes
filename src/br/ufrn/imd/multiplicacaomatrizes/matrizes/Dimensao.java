package br.ufrn.imd.multiplicacaomatrizes.matrizes;

public class Dimensao {
    private final Integer linhas;
    private final Integer colunas;

    private Dimensao(Integer linhas, Integer colunas) {
        assert linhas != null && linhas.compareTo(0) > 0 : "Quantidade de linhas tem que ser maior que 0";
        assert colunas != null && !colunas.equals(0) : "Quantidade de linhas tem que ser maior que 0";

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

    @Override
    public String toString() {
        return linhas + " " + colunas;
    }
}
