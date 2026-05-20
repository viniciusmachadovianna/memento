package com.hamburgueria.pedido;

public class HamburguerOriginator {
    private String descricao;
    private double preco;

    public void setEstado(String descricao, double preco) {
        this.descricao = descricao;
        this.preco = preco;
    }

    public HamburguerMemento criarMemento() {
        return new HamburguerMemento(this.descricao, this.preco);
    }

    public void restaurar(HamburguerMemento memento) {
        this.descricao = memento.getDescricao();
        this.preco = memento.getPreco();
    }

    public void mostrar() {
        System.out.println("Item: " + descricao + " | Preço: R$ " + preco);
    }
}