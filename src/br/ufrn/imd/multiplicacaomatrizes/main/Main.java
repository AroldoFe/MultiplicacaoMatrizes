package br.ufrn.imd.multiplicacaomatrizes.main;

import br.ufrn.imd.multiplicacaomatrizes.arquivo.EscritaArquivo;
import br.ufrn.imd.multiplicacaomatrizes.enums.TipoMultiplicacao;
import br.ufrn.imd.multiplicacaomatrizes.matrizes.Matriz;

import java.util.List;

public class Main {
    private static final int MAXIMA_VEZES_EXECUCAO = 20;

    public static void main(String[] args) {
        System.out.println("Recuperando argumentos");
        Argumentos argumentos = Argumentos.of(List.of(args));

        Matriz matrizA = argumentos.getMatrizA();
        Matriz matrizB = argumentos.getMatrizB();

        Matriz matrizC = null;

        TipoMultiplicacao tipoMultiplicacao = argumentos.getTipoMultiplicacao();

        for (int i = 0; i < MAXIMA_VEZES_EXECUCAO; i++) {
            System.out.println("Iterando pela " + i + "-ésima vez");
            final Matriz matrizResultante;

            long tempoInicial = System.nanoTime();
            System.out.println("Multiplicando matrízes");
            if (tipoMultiplicacao == TipoMultiplicacao.C) {
                matrizResultante = matrizA.multiplicarConcorrente(matrizB);
            } else {
                matrizResultante = matrizA.multiplicarSequencial(matrizB);
            }
            long tempoFinalSegundos = (System.nanoTime() - tempoInicial);

            final var matrizANome = matrizA.getNome() + matrizA.getDimensao().toString("x");
            final var matrizBNome = matrizB.getNome() + matrizB.getDimensao().toString("x");

            System.out.println("Registrando CSV");
            EscritaArquivo.registrarCSV(matrizANome, matrizBNome, tipoMultiplicacao, i, tempoFinalSegundos);

            if (matrizC == null) {
                matrizC = matrizResultante;
            }
        }

        System.out.println("Registrando resultado da multiplicação");
        EscritaArquivo.escrever(matrizC, tipoMultiplicacao);
    }
}
