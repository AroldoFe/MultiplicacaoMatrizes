package br.ufrn.imd.multiplicacaomatrizes.matrizes;

import br.ufrn.imd.multiplicacaomatrizes.multiplicacao.Multiplicacao;
import br.ufrn.imd.multiplicacaomatrizes.utils.AssertionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Matriz
 *
 * @author Aroldo Felix
 */
public class Matriz {
    private final String nome;
    private final Dimensao dimensao;
    private final Integer[][] matriz;

    private Matriz(String nome, Dimensao dimensao, Integer[][] matriz) {
        AssertionUtils.makeSure(matriz != null, "Matriz não pode ser nula");
        AssertionUtils.makeSure(AssertionUtils.isNotEmpty(nome), "Defina um nome para a matriz");

        this.nome = nome;
        this.dimensao = dimensao;
        this.matriz = matriz;
    }

    public static Matriz of(String nome, Integer linhas, Integer colunas, Integer[][] matriz) {
        return of(nome, Dimensao.of(linhas, colunas), matriz);
    }

    public static Matriz of(String nome, Dimensao dimensao, Integer[][] matriz) {
        return new Matriz(nome, dimensao, matriz);
    }

    public String getNome() {
        return nome;
    }

    public Dimensao getDimensao() {
        return this.dimensao;
    }

    public Integer[][] getMatriz() {
        return matriz;
    }

    /**
     * Saber se a multiplicação de matrizes pode ocorrer
     *
     * @param outra - Outra matriz
     * @return Se pode ocorrer a multiplicação
     */
    protected boolean isMultiplicacaoInvalida(Matriz outra) {
        return !this.getDimensao().getColunas().equals(outra.getDimensao().getLinhas());
    }

    /**
     * Implementação de multiplicação sequencial
     *
     * @param outra - Outra matriz
     * @return Matriz resultante de this x outra
     */
    public Matriz multiplicarSequencial(Matriz outra) {

        if (this.isMultiplicacaoInvalida(outra)) {
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

        final var nomeMatrizFinal = "C";

        return Matriz.of(nomeMatrizFinal, qtdLinhas, qtdColunas, matrizDadosC);
    }

    /**
     * Implementação de multiplicação concorrente
     *
     * @param outra - Outra matriz
     * @return Matriz resultante de this x outra
     */
    public Matriz multiplicarConcorrente(Matriz outra) {

        if (this.isMultiplicacaoInvalida(outra)) {
            final var dimensaoNaoSuportada = this.getDimensao().getColunas() + " <> " + outra.getDimensao().getLinhas();
            throw new IllegalArgumentException("Não é possível multiplicar as matrizes pois #Colunas de A <> #Linhas de B (" + dimensaoNaoSuportada + ")");
        }

        final var qtdLinhas = this.getDimensao().getLinhas();
        final var qtdColunas = outra.getDimensao().getColunas();

        final var matrizDadosC = new Integer[qtdLinhas][qtdColunas];


        Collection<Thread> threads = new ArrayList<>();

        // Calculando a linha
        for (int i = 0; i < qtdLinhas; i++) {
            Thread executante = Multiplicacao.of(this, outra, matrizDadosC, i);
            threads.add(executante);
            executante.start();
        }

        // Esperando todas as threads executarem
        threads.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        final var nomeMatrizFinal = "C";

        return Matriz.of(nomeMatrizFinal, qtdLinhas, qtdColunas, matrizDadosC);
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
