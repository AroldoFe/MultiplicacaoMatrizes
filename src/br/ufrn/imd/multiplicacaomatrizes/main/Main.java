package br.ufrn.imd.multiplicacaomatrizes.main;

import br.ufrn.imd.multiplicacaomatrizes.arquivo.EscritaArquivo;
import br.ufrn.imd.multiplicacaomatrizes.enums.TipoMultiplicacao;
import br.ufrn.imd.multiplicacaomatrizes.matrizes.Matriz;

import java.util.List;

public class Main {
    private static final int MAXIMA_VEZES_EXECUCAO = 20;

    public static void main(String[] args) {
        Argumentos argumentos = Argumentos.of(List.of(args));

        Matriz matrizA = argumentos.getMatrizA();
        Matriz matrizB = argumentos.getMatrizB();

        Matriz matrizC = null;

        TipoMultiplicacao tipoMultiplicacao = argumentos.getTipoMultiplicacao();

        for (int i = 0; i < MAXIMA_VEZES_EXECUCAO; i++) {
            final Matriz matrizResultante;

            long tempoInicial = System.nanoTime();
            if (tipoMultiplicacao == TipoMultiplicacao.C) {
                matrizResultante = matrizA.multiplicarConcorrente(matrizB);
            } else {
                matrizResultante = matrizA.multiplicarSequencial(matrizB);
            }
            long tempoFinalSegundos = (System.nanoTime() - tempoInicial);

            final var matrizANome = matrizA.getNome() + matrizA.getDimensao().toString("x");
            final var matrizBNome = matrizB.getNome() + matrizB.getDimensao().toString("x");

            EscritaArquivo.registrarCSV(matrizANome, matrizBNome, i, tempoFinalSegundos);

            if (matrizC == null) {
                matrizC = matrizResultante;
            }
        }

        EscritaArquivo.escrever(matrizC, tipoMultiplicacao);
    }
}
