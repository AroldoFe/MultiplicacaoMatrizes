package br.ufrn.imd.multiplicacaomatrizes.main;

import br.ufrn.imd.multiplicacaomatrizes.arquivo.EscritaArquivo;
import br.ufrn.imd.multiplicacaomatrizes.enums.TipoMultiplicacao;
import br.ufrn.imd.multiplicacaomatrizes.matrizes.Matriz;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Argumentos argumentos = Argumentos.of(List.of(args));

        Matriz matrizA = argumentos.getMatrizA();
        Matriz matrizB = argumentos.getMatrizB();

        Matriz matrizC;

        TipoMultiplicacao tipoMultiplicacao = argumentos.getTipoMultiplicacao();

        if (tipoMultiplicacao == TipoMultiplicacao.C) {
            matrizC = matrizA.multiplicarConcorrente(matrizB);
        } else {
            matrizC = matrizA.multiplicarSequencial(matrizB);
        }

        EscritaArquivo.escrever(matrizC, tipoMultiplicacao);
    }
}
