package br.ufrn.imd.multiplicacaomatrizes.enums;

/**
 * Identificador da multiplicação
 *
 * @author Aroldo Felix
 */
public enum TipoMultiplicacao {
    C("Concorrente"),
    S("Sequencial");

    private final String descricao;

    TipoMultiplicacao(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
