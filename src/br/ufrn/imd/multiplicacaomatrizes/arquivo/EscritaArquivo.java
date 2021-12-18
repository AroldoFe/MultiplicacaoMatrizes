package br.ufrn.imd.multiplicacaomatrizes.arquivo;

import br.ufrn.imd.multiplicacaomatrizes.enums.TipoMultiplicacao;
import br.ufrn.imd.multiplicacaomatrizes.matrizes.Dimensao;
import br.ufrn.imd.multiplicacaomatrizes.matrizes.Matriz;
import br.ufrn.imd.multiplicacaomatrizes.utils.AssertionUtils;
import com.opencsv.CSVWriter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Classe responsável por escrever matriz em TXT e registrar dados no CSV
 *
 * @author Aroldo Felix
 */
public class EscritaArquivo {

    /**
     * Escrever matriz em um arquivo
     *
     * @param matriz            - Matriz a ser salva no arquivo
     * @param tipoMultiplicacao - Tipo da multiplicação (Sequencial ou Concorrente)
     */
    public static void escrever(Matriz matriz, TipoMultiplicacao tipoMultiplicacao) {
        final BufferedWriter buffWrite;
        final Dimensao dimensao = matriz.getDimensao();

        // Recuperando path para /resources
        final var url = ClassLoader.getSystemClassLoader().getResource("");

        AssertionUtils.makeSure(url != null, "Diretório resources não encontrado");

        final var pathToResource = url.getPath();

        final var pathToFile = "saida/matrizes/" + tipoMultiplicacao.getDescricao() + "/" + matriz.getNome() + dimensao.getLinhas() + "x" + dimensao.getColunas();

        // Caminho final para o arquivo
        final var path = pathToResource + pathToFile;

        File file = new File(path);
        file.getParentFile().mkdirs();

        try {
            final var fileWriter = new FileWriter(file);
            buffWrite = new BufferedWriter(fileWriter);
        } catch (IOException e) {
            throw new RuntimeException("Caminho do arquivo não aceito", e.getCause());
        }

        try {
            buffWrite.append(matriz.toString());
            buffWrite.flush();
        } catch (IOException e) {
            throw new RuntimeException("Não foi possível escrever no arquivo", e.getCause());
        }

        try {
            buffWrite.close();
        } catch (IOException e) {
            throw new RuntimeException("Não foi possível fechar o arquivo", e.getCause());
        }
    }

    /**
     * Registrar dados da multiplicação no CSV
     *
     * @param matrizA           - Identificador da matriz A
     * @param matrizB           - Identificador da matriz B
     * @param tipoMultiplicacao - Tipo da multiplicação (Sequencial ou Concorrente)
     * @param iteracao          - Iteração no momento
     * @param milisegundos      - Tempo demorado em milisegundos
     */
    public static void registrarCSV(String matrizA, String matrizB, TipoMultiplicacao tipoMultiplicacao, int iteracao, long milisegundos) {
        BufferedWriter bufferedWriter;

        final var appendOnFile = true;

        final var url = ClassLoader.getSystemClassLoader().getResource("");

        AssertionUtils.makeSure(url != null, "Diretório resources não encontrado");

        final var pathToResource = url.getPath();

        final var pathToFile = "saida/csv/registros.csv";

        final var path = pathToResource + pathToFile;

        AssertionUtils.makeSure(AssertionUtils.isNotEmpty(path), "Arquivo CSV não encontrado");

        try {
            final var fileWriter = new FileWriter(path, appendOnFile);
            bufferedWriter = new BufferedWriter(fileWriter);
        } catch (IOException e) {
            throw new RuntimeException("Caminho do arquivo não aceito", e.getCause());
        }

        CSVWriter csvWriter = new CSVWriter(bufferedWriter);

        final var dados = new String[]{matrizA, matrizB, tipoMultiplicacao.name(), Integer.toString(iteracao), Long.toString(milisegundos)};

        csvWriter.writeNext(dados);

        try {
            csvWriter.flush();
            csvWriter.close();
        } catch (IOException e) {
            throw new IllegalArgumentException("Não foi possível fechar o arquivo");
        }
    }
}
