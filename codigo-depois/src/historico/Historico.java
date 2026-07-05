package src.historico;

import java.util.ArrayDeque;
import java.util.Deque;

import src.memento.Memento;

public class Historico {

    private final Deque<Memento> historico = new ArrayDeque<>();

    public void adicionar(Memento memento) {
        historico.push(memento);
    }

    public Memento desfazer() {
        if (historico.isEmpty()) {
            return null;
        }

        return historico.pop();
    }

    public boolean estaVazio() {
        return historico.isEmpty();
    }
}
