package src.app;

import java.util.Scanner;

import src.editor.EditorTextoSimples;
import src.historico.Historico;
import src.memento.Memento;

public class EditorTexto {

    public static void main(String[] args) {

        EditorTextoSimples editor = new EditorTextoSimples();
        Historico historico = new Historico();

        // Salva o estado inicial
        historico.adicionar(editor.salvar());

        Scanner scanner = new Scanner(System.in);

        System.out.println("=== BEM-VINDO ===");
        System.out.println("Digite qualquer texto para escrever.");
        System.out.println("Digite 'Z' para desfazer.");
        System.out.println("Digite 'SAIR' para encerrar.");

        while (true) {

            System.out.println("\n--- Tela do Editor ---");
            System.out.println("Conteúdo: "
                    + (editor.getTexto().isEmpty() ? "[Vazio]" : editor.getTexto()));
            System.out.println("Cursor na posição: " + editor.getPosicaoCursor());
            System.out.println("----------------------");

            System.out.print("Digite algo: ");
            String entrada = scanner.nextLine();

            if (entrada.equalsIgnoreCase("SAIR")) {
                break;
            }

            if (entrada.equalsIgnoreCase("Z")) {

                Memento estadoAnterior = historico.desfazer();

                if (estadoAnterior != null) {
                    editor.restaurar(estadoAnterior);
                } else {
                    System.out.println("Nada para desfazer.");
                }

            } else {

                historico.adicionar(editor.salvar());
                editor.escrever(entrada);

            }

        }

        scanner.close();
    }
}
