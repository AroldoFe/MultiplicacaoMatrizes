package br.ufrn.imd.multiplicacaomatrizes.matrizes;

public class MatrizSequencial extends Matriz {

    private MatrizSequencial(Integer linhas, Integer colunas, Integer[][] matriz) {
        super(Dimensao.of(linhas, colunas), matriz);
    }

    public static MatrizSequencial of(Integer linhas, Integer colunas, Integer[][] matriz) {
        return new MatrizSequencial(linhas, colunas, matriz);
    }

    @Override
    public boolean podeMultiplicar(Matriz outra) {
        return false;
    }

    @Override
    public Integer[][] multiplicar(Matriz outra) {
        return new Integer[0][];
    }
}
