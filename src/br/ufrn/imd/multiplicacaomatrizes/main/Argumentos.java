package br.ufrn.imd.multiplicacaomatrizes.main;

import br.ufrn.imd.multiplicacaomatrizes.arquivo.LeituraArquivo;
import br.ufrn.imd.multiplicacaomatrizes.enums.TipoMultiplicacao;
import br.ufrn.imd.multiplicacaomatrizes.matrizes.Matriz;
import br.ufrn.imd.multiplicacaomatrizes.utils.AssertionUtils;

import java.util.List;

public class Argumentos {
    private final Matriz matrizA;
    private final Matriz matrizB;
    private final TipoMultiplicacao tipoMultiplicacao;

    private Argumentos(Matriz matrizA, Matriz matrizB, TipoMultiplicacao tipoMultiplicacao) {
        this.matrizA = matrizA;
        this.matrizB = matrizB;
        this.tipoMultiplicacao = tipoMultiplicacao;
    }

    public static Argumentos of(List<String> args) {
        AssertionUtils.makeSure(args != null && args.size() > 1, "Argumentos insuficientes");

        final String tipoMultiplicacaoValue = args.get(args.size() - 1);
        AssertionUtils.makeSure(tipoMultiplicacaoValue.matches("[CS]"), "Tipo de multiplicação não suportado");

        final var matrizA = recuperarMatriz("A", args);
        final var matrizB = recuperarMatriz("B", args);

        final var tipoMultiplicacao = TipoMultiplicacao.valueOf(tipoMultiplicacaoValue);
        return new Argumentos(matrizA, matrizB, tipoMultiplicacao);
    }

    private static Matriz recuperarMatriz(String matrizId, List<String> args) {
        final String tamanhoMatriz = args.get(args.size() - 2);
        AssertionUtils.makeSure(tamanhoMatriz.matches("[0-9]+"), "Tamanho inválido da matriz");

        final var urlMatriz = ClassLoader.getSystemResource("entrada/matrizes/" + matrizId + tamanhoMatriz + "x" + tamanhoMatriz + ".txt");

        AssertionUtils.makeSure(AssertionUtils.isNotEmpty(urlMatriz), "Matriz " + matrizId + " não encontrada");

        return LeituraArquivo.ler(matrizId, urlMatriz.getPath());

    }

    public Matriz getMatrizA() {
        return matrizA;
    }

    public Matriz getMatrizB() {
        return matrizB;
    }

    public TipoMultiplicacao getTipoMultiplicacao() {
        return tipoMultiplicacao;
    }
}
