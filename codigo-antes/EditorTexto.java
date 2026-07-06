import java.util.ArrayList;
import java.util.List;

/**
 * VERSÃO: Código "Antes" (Situação-Problema)
 * * PROBLEMAS DE DESIGN IDENTIFICADOS:
 * 1. Quebra de Encapsulamento: O Editor expõe seus atributos privados via 
 * getters e setters públicos para que o histórico funcione.
 * 2. Alto Acoplamento: A classe Historico conhece os detalhes internos do Editor,
 * salvando cada variável individualmente em listas separadas.
 */
class EditorTextoSimples {
    private String texto;
    private int posicaoCursor;

    public EditorTextoSimples() {
        this.texto = "";
        this.posicaoCursor = 0;
    }

    public void escrever(String novoTexto) {
        this.texto += novoTexto;
        this.posicaoCursor = this.texto.length();
    }

    public void mostrarEstadoAtual() {
        System.out.println("--- Tela do Editor ---");
        System.out.println("Conteúdo: " + texto);
        System.out.println("Cursor na posição: " + posicaoCursor);
        System.out.println("----------------------\n");
    }

    // PROBLEMA: Métodos públicos que expõem os dados internos do Editor.
    public String getTexto() { 
        return texto; 
    }
    
    public void setTexto(String texto) { 
        this.texto = texto; 
    }

    public int getPosicaoCursor() { 
        return posicaoCursor; 
    }
    
    public void setPosicaoCursor(int posicaoCursor) { 
        this.posicaoCursor = posicaoCursor; 
    }
}

class Historico {
    // PROBLEMA: O histórico gerencia listas paralelas de tipos brutos (String e int)
    // para espelhar as variáveis do editor, pois não existe uma classe Memento.
    private List<String> historicoTextos = new ArrayList<>();
    private List<Integer> historicoCursores = new ArrayList<>();

    public void salvar(EditorTextoSimples editor) {
        // O histórico invade o editor usando os getters públicos para extrair os dados
        historicoTextos.add(editor.getTexto());
        historicoCursores.add(editor.getPosicaoCursor());
    }

    public void desfazer(EditorTextoSimples editor) {
        if (historicoTextos.isEmpty()) {
            System.out.println("Nenhum estado para desfazer.");
            return;
        }

        int ultimoIndice = historicoTextos.size() - 1;
        historicoTextos.remove(ultimoIndice);
        historicoCursores.remove(ultimoIndice);

        if (!historicoTextos.isEmpty()) {
            int indiceAnterior = historicoTextos.size() - 1;
            editor.setTexto(historicoTextos.get(indiceAnterior));
            editor.setPosicaoCursor(historicoCursores.get(indiceAnterior));
        } else {
            editor.setTexto("");
            editor.setPosicaoCursor(0);
        }
    }
}

// O nome desta classe coincide exatamente com o nome do arquivo (EditorTexto.java)
public class EditorTexto {
    public static void main(String[] args) {
        EditorTextoSimples editor = new EditorTextoSimples();
        Historico historico = new Historico();

        // Fluxo normal funcionando com as suas frases personalizadas!
        editor.escrever("Olá pessoas! ");
        historico.salvar(editor); 
        editor.mostrarEstadoAtual();

        editor.escrever("Essa é minha disciplina favorita.");
        historico.salvar(editor); 
        editor.mostrarEstadoAtual();

        System.out.println("<<< Usuário clicou em DESFAZER >>>");
        historico.desfazer(editor);
        editor.mostrarEstadoAtual();

        // PROVA DO ERRO DE DESIGN: Invasão externa direta burlando o histórico
        System.out.println("<<< Invasão Externa >>>");
        editor.setTexto("TEXTO CORROMPIDO POR OUTRA CLASSE"); 
        editor.mostrarEstadoAtual();
    }
}
