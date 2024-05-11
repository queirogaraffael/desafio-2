package com.gerenciadorDeTarefas.view;

import javax.swing.JOptionPane;

public class GerenciadorTarefasView {

	public static String menuPrincipalView() {

		// implementa data de modificacao
		// conexao com banco de dados(mongodb)
		// metodo que separa as tarefas concluidas e nao concluidas (por data e marcada)
		// no inicio // analizar
		// implementar Ordenar por // // data// adicionada

		Object[] opcoesMenu = { "Adicionar Tarefa", "Visualizar Tarefas nao concluidas",
				"Visualizar tarefas concluidas", "Marcar tarefa como concluida", "Desmarcar tarefa como concluida",
				"Modificar tarefa", "Ordenar por", "Remover Tarefa", "Sair" };

		Object opcaoSelecionada = JOptionPane.showInputDialog(null, "Escolha uma opcao", "Mene Principal",
				JOptionPane.INFORMATION_MESSAGE, null, opcoesMenu, opcoesMenu[0]);

		if (opcaoSelecionada != null) {
			return opcaoSelecionada.toString();
		}else {
			return "";
		}

	}

}
