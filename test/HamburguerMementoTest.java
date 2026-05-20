package com.hamburgueria.pedido;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HamburguerMementoTest {

    private HamburguerOriginator lanche;
    private HistoricoPedidos historico;

    @BeforeEach
    void setUp() {
        lanche = new HamburguerOriginator();
        historico = new HistoricoPedidos();
    }

    @Test
    void deveRestaurarEstadoInicial() {
        lanche.setEstado("X-Burger Simples", 20.00);
        HamburguerMemento mementoInicial = lanche.criarMemento();
        historico.adicionarMemento(mementoInicial);

        lanche.setEstado("X-Burger Turbinado com Bacon", 25.00);
        
        HamburguerMemento estadoSalvo = historico.desfazer();
        assertNotNull(estadoSalvo, "O memento recuperado não deveria ser nulo");
        lanche.restaurar(estadoSalvo);

        HamburguerMemento estadoAtual = lanche.criarMemento();
        
        assertEquals("X-Burger Simples", estadoAtual.getDescricao(), "A descrição deveria ter voltado para o original");
        assertEquals(20.00, estadoAtual.getPreco(), 0.001, "O preço deveria ter voltado para R$ 20.00");
    }

    @Test
    void deveSuportarMultiplosDesfazeres() {
        lanche.setEstado("Lanche Base", 10.00);
        historico.adicionarMemento(lanche.criarMemento());

        lanche.setEstado("Lanche Base + Queijo", 12.00);
        historico.adicionarMemento(lanche.criarMemento());

        lanche.setEstado("Lanche Base + Queijo + Ovo", 15.00);

        lanche.restaurar(historico.desfazer());
        HamburguerMemento check1 = lanche.criarMemento();
        assertEquals("Lanche Base + Queijo", check1.getDescricao());
        assertEquals(12.00, check1.getPreco());

        lanche.restaurar(historico.desfazer());
        HamburguerMemento check2 = lanche.criarMemento();
        assertEquals("Lanche Base", check2.getDescricao());
        assertEquals(10.00, check2.getPreco());
    }

    @Test
    void deveRetornarNuloComHistoricoVazio() {
        HamburguerMemento mementoDesejado = historico.desfazer();
        assertNull(mementoDesejado, "Desfazer em um histórico vazio deve retornar null");
    }
}