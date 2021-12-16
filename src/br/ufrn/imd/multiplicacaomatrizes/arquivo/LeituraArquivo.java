package br.ufrn.imd.multiplicacaomatrizes.arquivo;

import br.ufrn.imd.multiplicacaomatrizes.matrizes.Dimensao;
import br.ufrn.imd.multiplicacaomatrizes.matrizes.Matriz;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LeituraArquivo {

    public static Matriz ler(String path) {
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
                assert linhaConcatenada != null : "Matriz não possui quantidade de linhas definida na dimensão";

                final var dadosLinha = linhaConcatenada.split(" ");

                for (int coluna = 0; coluna < dimensao.getLinhas(); coluna++) {
                    dadosMatriz[linha][coluna] = Integer.parseInt(dadosLinha[coluna]);
                }
            }

            buffRead.close();

            return Matriz.of(dimensao, dadosMatriz);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static Dimensao recuperarDimensao(String linha) {
        final String separador = " ";

        assert linha != null : "Dimensão não pode ser nula";
        assert linha.length() >= 3 : "É necessário pelo menos 3 elementos";
        assert linha.contains(separador) : "Separador não encontrado";

        final List<Integer> dimensao = Arrays
                .stream(linha.split(separador))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        assert dimensao.size() == 2 : "A dimensão deve conter quantidade de linhas e colunas";

        return Dimensao.of(dimensao.get(0), dimensao.get(1));
    }
}
