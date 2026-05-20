package com.hamburgueria.pedido;

final class HamburguerMemento {
    private final String descricao;
    private final double preco;

    HamburguerMemento(String descricao, double preco) {
        this.descricao = descricao;
        this.preco = preco;
    }

    String getDescricao() {
        return descricao;
    }

    double getPreco() {
        return preco;
    }
}