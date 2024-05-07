package com.gerenciadorDeTarefas.view;

import javax.swing.JOptionPane;

public class GerenciadorTarefasView {

	public static String menuPrincipalView() {

		// implementar depois com hora
		// adiciona um metodo que pega a data e avalia se ja foi concluido ou nao e
		// modifica o booleano // no inicio
		// analisar a criacao de categorias para prioridade
		// conectar a um banco de dados nao relacional
		// adicionar hora de modificacao
		// implementar Ordenar por // // data// adicionada

		Object[] opcoesMenu = { "Adicionar Tarefa", "Visualizar Tarefas nao concluidas",
				"Visualizar tarefas concluidas", "Marcar tarefa como concluida", "Desmarcar tarefa como concluida",
				"Modificar tarefa", "Ordenar por", "Remover Tarefa", "Sair" };

		Object opcaoSelecionada = JOptionPane.showInputDialog(null, "Escolha uma opcao", "Opcao",
				JOptionPane.INFORMATION_MESSAGE, null, opcoesMenu, opcoesMenu[0]);

		return opcaoSelecionada.toString();

	}

}
