package br.ufrn.imd.multiplicacaomatrizes.main;

import br.ufrn.imd.multiplicacaomatrizes.arquivo.LeituraArquivo;
import br.ufrn.imd.multiplicacaomatrizes.enums.TipoMultiplicacao;
import br.ufrn.imd.multiplicacaomatrizes.matrizes.Matriz;

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
        assert args != null && args.size() > 6 : "Argumentos vazios";

        final int argumentoMatrizA = args.indexOf("-a");
        assert argumentoMatrizA > 0 : "Argumento: Matriz A não encontrada";

        final String pathMatrizA = args.get(argumentoMatrizA + 1);
        assert !pathMatrizA.startsWith("-") : "Caminho da Matriz A inválido";

        final int argumentoMatrizB = args.indexOf("-b");
        assert argumentoMatrizB > 0 : "Argumento: Matriz B não encontrada";

        final String pathMatrizB = args.get(argumentoMatrizB + 1);
        assert !pathMatrizB.startsWith("-") : "Caminho da Matriz B inválido";

        final int argumentoTipoMultiplicacao = args.indexOf("-t");
        assert argumentoTipoMultiplicacao > 0 : "Argumento: Tipo da multiplicação não encontrado";

        final String tipoMultiplicacaoValue = args.get(argumentoTipoMultiplicacao + 1);
        assert tipoMultiplicacaoValue != null && tipoMultiplicacaoValue.length() == 1 : "Tipo da multiplicação não encontrado";

        final var tipoMultiplicacao = TipoMultiplicacao.valueOf(tipoMultiplicacaoValue);
        final var matrizA = LeituraArquivo.ler(pathMatrizA);
        final var matrizB = LeituraArquivo.ler(pathMatrizB);

        return new Argumentos(matrizA, matrizB, tipoMultiplicacao);
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
