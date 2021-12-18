package br.ufrn.imd.multiplicacaomatrizes.multiplicacao;

import br.ufrn.imd.multiplicacaomatrizes.matrizes.Matriz;
import br.ufrn.imd.multiplicacaomatrizes.utils.AssertionUtils;

/**
 * Classe responsável pela geração de uma linha da multiplicação de matrizes
 *
 * @author Aroldo Felix
 */
public class Multiplicacao extends Thread {
    private final Matriz a;
    private final Matriz b;
    private final Integer[][] resultante;
    private final int indice;

    private Multiplicacao(String nomeThread, Matriz a, Matriz b, Integer[][] resultante, int indice) {
        super(nomeThread);
        this.a = a;
        this.b = b;
        this.resultante = resultante;
        this.indice = indice;
    }

    /**
     * Criação multiplicação de de AxB na linha indice
     *
     * @param a          - Matriz A
     * @param b          - Matriz B
     * @param resultante - Resultado da linha[indice] de AxB
     * @param indice     - Linha a ser gerada
     * @return Multiplicação
     */
    public static Multiplicacao of(Matriz a, Matriz b, Integer[][] resultante, int indice) {
        AssertionUtils.makeSure(AssertionUtils.isNotEmpty(a), "Matriz A nula");
        AssertionUtils.makeSure(AssertionUtils.isNotEmpty(b), "Matriz B nula");
        AssertionUtils.makeSure(AssertionUtils.isNotEmpty(resultante), "Matriz resultante nula");
        AssertionUtils.makeSure(AssertionUtils.isNotEmpty(indice), "Índice nulo");

        final String nomeThread = "ThreadMatrizLinha" + indice;

        return new Multiplicacao(nomeThread, a, b, resultante, indice);
    }

    @Override
    public void run() {
        final var jMaximo = this.b.getDimensao().getColunas();
        final var kMaximo = this.a.getDimensao().getColunas();

        final var matrizA = this.a.getMatriz();
        final var matrizB = this.b.getMatriz();

        for (int j = 0; j < jMaximo; j++) {
            var soma = 0;
            for (int k = 0; k < kMaximo; k++) {
                soma += matrizA[indice][k] + matrizB[k][j];
            }
            this.resultante[indice][j] = soma;
        }
    }
}
