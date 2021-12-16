package br.ufrn.imd.multiplicacaomatrizes.arquivo;

import br.ufrn.imd.multiplicacaomatrizes.enums.TipoMultiplicacao;
import br.ufrn.imd.multiplicacaomatrizes.matrizes.Dimensao;
import br.ufrn.imd.multiplicacaomatrizes.matrizes.Matriz;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class EscritaArquivo {

    public static void escrever(Matriz matriz, TipoMultiplicacao tipoMultiplicacao) {
        final BufferedWriter buffWrite;
        final Dimensao dimensao = matriz.getDimensao();
        final var path = "/resources/saida/matrizes/" + tipoMultiplicacao.getDescricao() + "/C" + dimensao.getLinhas() + "x" + dimensao.getColunas();

        try {
            final var fileWriter = new FileWriter(path);
            buffWrite = new BufferedWriter(fileWriter);
        } catch (IOException e) {
            throw new RuntimeException("Caminho do arquivo não aceito", e.getCause());
        }

        try {
            buffWrite.append(matriz.toString());
        } catch (IOException e) {
            throw new RuntimeException("Não foi possível escrever no arquivo", e.getCause());
        }

        try {
            buffWrite.close();
        } catch (IOException e) {
            throw new RuntimeException("Não foi possível fechar o arquivo", e.getCause());
        }
    }
}
