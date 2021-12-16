package br.ufrn.imd.multiplicacaomatrizes.matrizes;

public abstract class Matriz {
    protected final Dimensao dimensao;
    protected final Integer[][] matriz;

    protected Matriz(Dimensao dimensao, Integer[][] matriz) {
        assert matriz != null;
        this.dimensao = dimensao;
        this.matriz = matriz;
    }

    public Dimensao getDimensao() {
        return this.dimensao;
    }

    public Integer[][] getMatriz() {
        return matriz;
    }

    protected boolean podeMultiplicar(Matriz outra) {
        return this.getDimensao().getColunas().equals(outra.getDimensao().getLinhas());
    }

    abstract Integer[][] multiplicar(Matriz outra);
}
