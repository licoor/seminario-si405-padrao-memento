package src.editor;

import src.memento.Memento;

public class EditorTextoSimples {

    private String texto;
    private int posicaoCursor;

    public EditorTextoSimples() {
        texto = "";
        posicaoCursor = 0;
    }

    public void escrever(String novoTexto) {
        texto += novoTexto;
        posicaoCursor = texto.length();
    }

    public Memento salvar() {
        return new Memento(texto, posicaoCursor);
    }

    public void restaurar(Memento memento) {
        if (memento == null) {
            return;
        }

        texto = memento.getTexto();
        posicaoCursor = memento.getPosicaoCursor();
    }

    public String getTexto() {
        return texto;
    }

    public int getPosicaoCursor() {
        return posicaoCursor;
    }
}
