package com.gerenciadorDeTarefas.view;

public class GerenciadorTarefasView {

	public static String menuPrincipalView() {
		StringBuilder menu = new StringBuilder();
		// implementar depois com hora
		// adiciona um metodo que pega a data e avalia se ja foi concluido ou nao e
		// modifica o booleano
		// analisar a criacao de categorias para prioridade
		menu.append("Bem-vindo ao Gerenciador de Tarefas\n\n");
		menu.append("Menu:\n");
		menu.append("1. Adicionar Tarefa\n");
		menu.append("2. Visualizar tarefas\n"); // implementar
		menu.append("3. Visualizar Tarefas nao concluidas\n"); // booleano e data posterior a atual // implementar
		menu.append("4. Marcar tarefa como concluida\n"); // implementar
		menu.append("5. Desmarcar tarefa como concluida\n"); // implementar
		menu.append("6. Modificar tarefa\n"); // apenas nao concluidas // opcoes de todos atributos // implementar
		menu.append("7. Ordenar por\n"); // data// adicionada // implementar
		menu.append("8. Remover Tarefa\n"); // lista e seleciona // implementar
		menu.append("9. Sair");
		menu.append("\nEscolha uma opcao: ");

		return menu.toString();

	}

}
