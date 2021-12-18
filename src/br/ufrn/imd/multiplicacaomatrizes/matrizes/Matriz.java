package br.ufrn.imd.multiplicacaomatrizes.matrizes;

import br.ufrn.imd.multiplicacaomatrizes.utils.AssertionUtils;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Matriz {
    private final Dimensao dimensao;
    private final Integer[][] matriz;

    protected Matriz(Dimensao dimensao, Integer[][] matriz) {
        AssertionUtils.makeSure(matriz != null, "Matriz não pode ser nula");

        this.dimensao = dimensao;
        this.matriz = matriz;
    }

    public static Matriz of(Integer linhas, Integer colunas, Integer[][] matriz) {
        return of(Dimensao.of(linhas, colunas), matriz);
    }

    public static Matriz of(Dimensao dimensao, Integer[][] matriz) {
        return new Matriz(dimensao, matriz);
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

    public Matriz multiplicarSequencial(Matriz outra) {

        if (!this.podeMultiplicar(outra)) {
            final var dimensaoNaoSuportada = this.getDimensao().getColunas() + " <> " + outra.getDimensao().getLinhas();
            throw new IllegalArgumentException("Não é possível multiplicar as matrizes pois #Colunas de A <> #Linhas de B (" + dimensaoNaoSuportada + ")");
        }

        final var qtdLinhas = this.getDimensao().getLinhas();
        final var qtdColunas = outra.getDimensao().getColunas();
        final var kMaximo = this.getDimensao().getColunas();

        final var matrizDadosA = this.getMatriz();
        final var matrizDadosB = outra.getMatriz();
        final var matrizDadosC = new Integer[qtdLinhas][qtdColunas];

        for (int i = 0; i < qtdLinhas; i++) {
            for (int j = 0; j < qtdColunas; j++) {
                var soma = 0;
                for (int k = 0; k < kMaximo; k++) {
                    soma += matrizDadosA[i][k] + matrizDadosB[k][j];
                }
                matrizDadosC[i][j] = soma;
            }
        }

        return Matriz.of(qtdLinhas, qtdColunas, matrizDadosC);
    }

    public Matriz multiplicarConcorrente(Matriz outra) {

        return null;
    }

    @Override
    public String toString() {
        final var delimitador = " ";
        final var delimitadorLinha = "\n";

        return
                dimensao.toString(delimitador) +
                        delimitadorLinha +
                        Arrays
                                .stream(this.getMatriz())
                                .map(it ->
                                        Arrays
                                                .stream(it)
                                                .map(Object::toString)
                                                .collect(Collectors.joining(delimitador))
                                ).collect(Collectors.joining(delimitadorLinha));
    }
}
