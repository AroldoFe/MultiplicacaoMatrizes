package br.ufrn.imd.multiplicacaomatrizes.matrizes;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

public class MatrizTest {

    @Test
    public void of_deveLancarExceptionNomeNuloVazio() {
        assertThrows(IllegalArgumentException.class, () -> Matriz.of(null, 5, 7, new Integer[5][7]));

        assertThrows(IllegalArgumentException.class, () -> Matriz.of("", 5, 7, new Integer[5][7]));
    }

    @Test(expected = IllegalArgumentException.class)
    public void of_deveLancarExceptionDimensaoNula() {
        Matriz.of("Nome Valido", null, new Integer[1][1]);
    }

    @Test(expected = IllegalArgumentException.class)
    public void of_deveLancarExceptionDimensaoInvalida() {
        Matriz.of("Nome Valido", 0, 5, new Integer[1][1]);
    }

    @Test
    public void of_deveLancarExceptionMatrizNulaVazia() {

        assertThrows(IllegalArgumentException.class, () -> Matriz.of("Nome Valido", Dimensao.of(5, 5), null));

        assertThrows(IllegalArgumentException.class, () -> Matriz.of("Nome Valido", Dimensao.of(5, 5), new Integer[0][0]));

        assertThrows(IllegalArgumentException.class, () -> Matriz.of("Nome Valido", Dimensao.of(5, 5), new Integer[5][0]));

    }

    @Test
    public void of_deveLancarExceptionMatrizOrdemDiferenteDimensao() {

        assertThrows(IllegalArgumentException.class, () -> Matriz.of("Nome Valido", Dimensao.of(5, 5), new Integer[2][5]));

        assertThrows(IllegalArgumentException.class, () -> Matriz.of("Nome Valido", Dimensao.of(5, 5), new Integer[5][1]));

    }

    @Test
    public void of_deveRetornarMatrizValida() {
        final var linhas = 2;
        final var colunas = 3;
        Integer[][] dados = new Integer[linhas][colunas];

        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                dados[i][j] = i + j;
            }
        }

        final var matriz = Matriz.of("A", linhas, colunas, dados);

        assertEquals("A", matriz.getNome());
        assertEquals(Integer.valueOf(2), matriz.getDimensao().getLinhas());
        assertEquals(Integer.valueOf(3), matriz.getDimensao().getColunas());
        assertArrayEquals(dados, matriz.getMatriz());
    }

    @Test
    public void isMultiplicacaoValida_deveIdentificarMultiplicacaoInvalida() {
        final var linhas = 2;
        final var colunas = 3;
        Integer[][] dados = new Integer[linhas][colunas];

        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                dados[i][j] = i + j;
            }
        }

        final var matrizA = Matriz.of("A", linhas, colunas, dados);
        final var matrizB = Matriz.of("B", linhas, colunas, dados);

        assertTrue(matrizA.isMultiplicacaoInvalida(matrizB));
    }

    @Test
    public void multiplicar_deveLancarExceptionMultiplcacaoNaoSuportada() {
        final var linhas = 2;
        final var colunas = 3;
        Integer[][] dadosA = new Integer[linhas][colunas];
        Integer[][] dadosB = new Integer[linhas][colunas];

        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                dadosA[i][j] = i + j;
                dadosB[i][j] = i + j;
            }
        }

        final var matrizA = Matriz.of("A", linhas, colunas, dadosA);
        final var matrizB = Matriz.of("B", linhas, colunas, dadosB);

        assertThrows(IllegalArgumentException.class, () -> matrizA.multiplicarSequencial(matrizB));
        assertThrows(IllegalArgumentException.class, () -> matrizA.multiplicarConcorrente(matrizB));
    }

    @Test
    public void multiplicar_deveRetornarValorValido() {
        final var linhas = 2;
        final var colunas = 3;
        Integer[][] dadosA = new Integer[linhas][colunas];
        Integer[][] dadosB = new Integer[colunas][linhas];

        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                dadosA[i][j] = i + j;
                dadosB[j][i] = i + j;
            }
        }

        // Matriz resultante da multiplicação AxB
        Integer[][] dadosC = new Integer[linhas][linhas];
        dadosC[0][0] = 5;
        dadosC[0][1] = 8;
        dadosC[1][0] = 8;
        dadosC[1][1] = 14;

        final var matrizA = Matriz.of("A", linhas, colunas, dadosA);
        final var matrizB = Matriz.of("B", colunas, linhas, dadosB);
        final var matrizCSeq = matrizA.multiplicarSequencial(matrizB);
        final var matrizCConc = matrizA.multiplicarConcorrente(matrizB);


        assertEquals(Integer.valueOf(2), matrizCSeq.getDimensao().getLinhas());
        assertEquals(Integer.valueOf(2), matrizCSeq.getDimensao().getColunas());
        assertArrayEquals(dadosC, matrizCSeq.getMatriz());

        assertEquals(Integer.valueOf(2), matrizCConc.getDimensao().getLinhas());
        assertEquals(Integer.valueOf(2), matrizCConc.getDimensao().getColunas());
        assertArrayEquals(dadosC, matrizCConc.getMatriz());

        String matrizC = "2 2\n5 8\n8 14";
        assertEquals(matrizC, matrizCSeq.toString());
        assertEquals(matrizC, matrizCConc.toString());
    }

}
