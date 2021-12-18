package br.ufrn.imd.multiplicacaomatrizes.arquivo;

import br.ufrn.imd.multiplicacaomatrizes.matrizes.Dimensao;
import br.ufrn.imd.multiplicacaomatrizes.matrizes.Matriz;
import br.ufrn.imd.multiplicacaomatrizes.utils.AssertionUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Classe responsável por ler a matriz de um TXT
 *
 * @author Aroldo Felix
 */
public class LeituraArquivo {

    /**
     * Recuperar a matriz de um arquivo
     *
     * @param nome - Nome do arquivo
     * @param path - Caminho do arquivo
     * @return Matriz
     */
    public static Matriz ler(String nome, String path) {
        final BufferedReader buffRead;
        try {
            final var fileReader = new FileReader(path);
            buffRead = new BufferedReader(fileReader);
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Caminho do arquivo não aceito");
        }
        // Ler primeira linha
        try {
            final String primeiraLinha = buffRead.readLine();

            final Dimensao dimensao = recuperarDimensao(primeiraLinha);

            final Integer[][] dadosMatriz = new Integer[dimensao.getLinhas()][dimensao.getColunas()];

            for (int linha = 0; linha < dimensao.getLinhas(); linha++) {
                // Recuperar Linha
                final var linhaConcatenada = buffRead.readLine();
                AssertionUtils.makeSure(linhaConcatenada != null, "Matriz não possui quantidade de linhas definida na dimensão");

                final var dadosLinha = linhaConcatenada.split(" ");

                for (int coluna = 0; coluna < dimensao.getLinhas(); coluna++) {
                    dadosMatriz[linha][coluna] = Integer.parseInt(dadosLinha[coluna]);
                }
            }

            buffRead.close();

            return Matriz.of(nome, dimensao, dadosMatriz);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Recuperando a dimensão da matriz
     *
     * @param linha - Primeira linha do arquivo
     * @return Dimensão da matriz
     */
    private static Dimensao recuperarDimensao(String linha) {
        final String separador = " ";

        AssertionUtils.makeSure(linha != null, "Dimensão não pode ser nula");
        AssertionUtils.makeSure(linha.length() >= 3, "É necessário pelo menos 3 elementos");
        AssertionUtils.makeSure(linha.contains(separador), "Separador não encontrado");

        final List<Integer> dimensao = Arrays
                .stream(linha.split(separador))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        AssertionUtils.makeSure(dimensao.size() == 2, "A dimensão deve conter quantidade de linhas e colunas");

        return Dimensao.of(dimensao.get(0), dimensao.get(1));
    }
}
