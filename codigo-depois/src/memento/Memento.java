package src.memento;

public class Memento {
    private final String texto;
    private final int posicaoCursor;

    public Memento(String texto, int posicaoCursor) {
        this.texto = texto;
        this.posicaoCursor = posicaoCursor;
    }

    public String getTexto() {
        return texto;
    }

    public int getPosicaoCursor() {
        return posicaoCursor;
    }
}

