package com.hamburgueria.pedido;

import java.util.Stack;

public class HistoricoPedidos {
    private final Stack<HamburguerMemento> mementos = new Stack<>();

    public void adicionarMemento(HamburguerMemento m) {
        mementos.push(m);
    }

    public HamburguerMemento desfazer() {
        if (!mementos.isEmpty()) {
            return mementos.pop();
        }
        return null;
    }
}