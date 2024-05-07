package com.gerenciadorDeTarefas.controller;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JOptionPane;

import com.gerenciadorDeTarefas.constantes.ConstantesMenuPrincipal;
import com.gerenciadorDeTarefas.entities.Tarefa;
import com.gerenciadorDeTarefas.services.TarefaService;
import com.gerenciadorDeTarefas.util.ManipulacaoData;
import com.gerenciadorDeTarefas.view.GerenciadorTarefasView;

public class MenuPrincipalTarefaController {

	private Set<Tarefa> tarefas;

	public MenuPrincipalTarefaController() {
		tarefas = new HashSet<>();
	}

	public void exibirMenuPrincipal() {

		int opcaoMenuPrincipal = 0;

		do {

			try {

				opcaoMenuPrincipal = Integer
						.parseInt(JOptionPane.showInputDialog(GerenciadorTarefasView.menuPrincipalView()));

				switch (opcaoMenuPrincipal) {

				case (ConstantesMenuPrincipal.ADICIONAR):
					adicionaTarefa(tarefas);
					break;

				case (ConstantesMenuPrincipal.VISUALIZARNAOCONCLUIDA):
					visualizarTarefasNaoConcluidas(tarefas);
					break;

				case (ConstantesMenuPrincipal.VISUALIZARCONCLUIDA):
					visualizarTarefasConcluidas(tarefas);
					break;

				case (ConstantesMenuPrincipal.MARCARCONCLUIDA):
					marcarTarefaComoConcluida(tarefas);
					break;

				case (ConstantesMenuPrincipal.DESMARCARCONCLUIDA):
					desmarcarTarefaComoConcluida(tarefas);
					break;

				case (ConstantesMenuPrincipal.MODIFICAR):
					// implementar
					break;

				case (ConstantesMenuPrincipal.ORDERNAR):
					// implementar
					break;

				case (ConstantesMenuPrincipal.REMOVER):
					removerTarefa(tarefas);
					break;

				case (ConstantesMenuPrincipal.SAIR):
					JOptionPane.showMessageDialog(null, "Fim do programa");
					break;

				}

			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Entre com um valor valor numerico associado a uma opcao!");
			}

		} while (opcaoMenuPrincipal != ConstantesMenuPrincipal.SAIR);

	}

	private void adicionaTarefa(Set<Tarefa> tarefas) {

		String titulo = JOptionPane.showInputDialog("Titulo da tarefa: ");

		if (TarefaService.verificaSeJaExisteTarefaPeloTitulo(tarefas, titulo)) {

			JOptionPane.showMessageDialog(null, "Tarefa ja existe. Tente modificar ou remover.");

		} else {

			String descricao = JOptionPane.showInputDialog("Descricao da tarefa: ");

			Object[] opcoes = { "Sim", "Nao" };

			int opcaoData = JOptionPane.showOptionDialog(null, "Deseja adicionar data a tarefa ?\n1. Sim\n2. Nao",
					"Data", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opcoes, opcoes[0]);

			Tarefa tarefa = new Tarefa();
			tarefa.setTitulo(titulo);
			tarefa.setDescricao(descricao);
			tarefa.setStatusConcluida(false);

			if (opcaoData == 0) {
				String formatoData = "dd/MM/yyyy";
				String dataString = JOptionPane.showInputDialog("Digite uma data no formato dd/MM/yyyy");
				boolean formatoAprovado = ManipulacaoData.verificaFormatoData(dataString, formatoData);

				while (!formatoAprovado) {
					dataString = JOptionPane.showInputDialog("Digite uma data no formato dd/MM/yyyy");
				}
				LocalDate localDate = ManipulacaoData.retornaLocalDate(dataString);
				tarefa.setData(localDate);

			}

			tarefas.add(tarefa);

			JOptionPane.showMessageDialog(null, "Tarefa adicionada com sucesso!");
		}

	}

	private void visualizarTarefasNaoConcluidas(Set<Tarefa> tarefas) {

		String relatorioTarefasNaoConcluidas = TarefaService.geraRelatorioTarefasNaoConlcuidas(tarefas);

		if (relatorioTarefasNaoConcluidas.equals("")) {
			JOptionPane.showMessageDialog(null, "Lista de tarefas nao concluidas vazia.");
		} else {
			JOptionPane.showMessageDialog(null, relatorioTarefasNaoConcluidas);
		}

	}

	private void visualizarTarefasConcluidas(Set<Tarefa> tarefas) {

		String relatorioTarefasConcluidas = TarefaService.geraRelatorioTarefasConlcuidas(tarefas);

		if (relatorioTarefasConcluidas.equals("")) {
			JOptionPane.showMessageDialog(null, "Lista de tarefas concluidas vazia.");
		} else {
			JOptionPane.showMessageDialog(null, relatorioTarefasConcluidas);
		}

	}

	private void marcarTarefaComoConcluida(Set<Tarefa> tarefas) {

		String titulo = JOptionPane.showInputDialog("Titulo da tarefa: ");

		Tarefa tarefa = TarefaService.retornaTarefaPeloTitulo(tarefas, titulo);

		if (tarefa == null) {
			JOptionPane.showMessageDialog(null, "Tarefa nao encontrada!");
		} else {

			tarefa.setStatusConcluida(true);
			JOptionPane.showMessageDialog(null, "Tarefa marcada como conluida!");
		}

	}

	private void desmarcarTarefaComoConcluida(Set<Tarefa> tarefas) {

		String titulo = JOptionPane.showInputDialog("Titulo da tarefa: ");

		Tarefa tarefa = TarefaService.retornaTarefaPeloTitulo(tarefas, titulo);

		if (tarefa == null) {
			JOptionPane.showMessageDialog(null, "Tarefa nao encontrada!");
		} else {

			tarefa.setStatusConcluida(false);
			JOptionPane.showMessageDialog(null, "Tarefa desmarcada como conluida!");

		}

	}

	private void removerTarefa(Set<Tarefa> tarefas) {
		String tituloTarefaParaRemover = JOptionPane.showInputDialog("Titulo da tarefa que voce deseja remover: ");

		TarefaService.removeTarefa(tarefas, tituloTarefaParaRemover);
	}

}