package br.ufrn.imd.multiplicacaomatrizes.matrizes;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Matriz {
    private final Dimensao dimensao;
    private final Integer[][] matriz;

    protected Matriz(Dimensao dimensao, Integer[][] matriz) {
        assert matriz != null : "Matriz não pode ser nula";
        this.dimensao = dimensao;
        this.matriz = matriz;
    }

    public static Matriz of(Integer linhas, Integer colunas, Integer[][] matriz) {
        return of(Dimensao.of(linhas, colunas), matriz);
    }

    public static Matriz of(Dimensao dimensao, Integer[][] matriz) {
        return new Matriz(dimensao, matriz);
    }

    public Dimensao getDimensao() {
        return this.dimensao;
    }

    public Integer[][] getMatriz() {
        return matriz;
    }

    protected boolean podeMultiplicar(Matriz outra) {
        return this.getDimensao().getColunas().equals(outra.getDimensao().getLinhas());
    }

    public Matriz multiplicarSequencial(Matriz outra) {

        return null;
    }

    public Matriz multiplicarConcorrente(Matriz outra) {

        return null;
    }

    @Override
    public String toString() {
        final var delimitador = " ";
        final var delimitadorLinha = "\n";

        return
                dimensao.toString() +
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
