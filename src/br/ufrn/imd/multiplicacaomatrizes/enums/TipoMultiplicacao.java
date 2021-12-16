package br.ufrn.imd.multiplicacaomatrizes.enums;

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
