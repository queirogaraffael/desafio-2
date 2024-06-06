package com.gerenciadorDeTarefas.controller;

import java.time.LocalDate;

import javax.swing.JOptionPane;

import com.gerenciadorDeTarefas.MongoDB.MongoDBConnection;
import com.gerenciadorDeTarefas.commons.constantes.ConstantesMenuPrincipal;
import com.gerenciadorDeTarefas.commons.constantes.ConstantesOpcaoModificarTarefa;
import com.gerenciadorDeTarefas.commons.util.ManipulacaoData;
import com.gerenciadorDeTarefas.model.dao.DaoFactory;
import com.gerenciadorDeTarefas.model.dao.TarefaDao;
import com.gerenciadorDeTarefas.model.entities.Tarefa;
import com.gerenciadorDeTarefas.services.TarefaService;
import com.gerenciadorDeTarefas.view.GerenciadorTarefasView;

public class MenuPrincipalTarefaController {

	private TarefaDao tarefaDao;

	public MenuPrincipalTarefaController() {
		this.tarefaDao = DaoFactory.createMedicoDao();

	}

	public void exibirMenuPrincipal() {

		String opcaoMenuPrincipal = "";

		do {

			try {
				opcaoMenuPrincipal = GerenciadorTarefasView.menuPrincipalView();

				switch (opcaoMenuPrincipal) {

				case (ConstantesMenuPrincipal.ADICIONAR):
					adicionaTarefa();
					break;

				case (ConstantesMenuPrincipal.VISUALIZA_TAREFA_PELO_ID):
					visualizaTarefaPeloId();
					break;

				case (ConstantesMenuPrincipal.VISUALIZAR_NAO_CONCLUIDA):
					visualizaTarefasNaoConcluidas();
					break;

				case (ConstantesMenuPrincipal.VISUALIZAR_CONCLUIDA):
					visualizaTarefasConcluidas();
					break;

				case (ConstantesMenuPrincipal.MARCAR_CONCLUIDA):
					marcaTarefaComoConcluida();
					break;

				case (ConstantesMenuPrincipal.DESMARCAR_CONCLUIDA):
					desmarcaTarefaComoConcluida();
					break;

				case (ConstantesMenuPrincipal.MODIFICAR):
					modificaTarefa();
					break;

				case (ConstantesMenuPrincipal.ORDERNAR):
					// implementar
					break;

				case (ConstantesMenuPrincipal.REMOVER):
					removeTarefa();
					break;

				case (ConstantesMenuPrincipal.SAIR):
					MongoDBConnection.close();

				}
			} catch (NumberFormatException erro) {
				JOptionPane.showMessageDialog(null, "Valor invalido");
			}

		} while (!opcaoMenuPrincipal.equals(ConstantesMenuPrincipal.SAIR));

	}

	private void adicionaTarefa() {

		String titulo = JOptionPane.showInputDialog("Titulo da tarefa: ");

		if (TarefaService.verificaSeJaExisteTarefaPeloTitulo(tarefas, titulo)) {

			JOptionPane.showMessageDialog(null, "Tarefa ja existe. Tente modificar ou remover.");

		} else {

			String descricao = JOptionPane.showInputDialog("Descricao da tarefa: ");

			Object[] opcoes = { "Sim", "Nao" };

			int opcaoData = JOptionPane.showOptionDialog(null, "Deseja adicionar data na tarefa ?", "Data",
					JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opcoes, opcoes[0]);

			Tarefa tarefa = new Tarefa();
			tarefa.setTitulo(titulo);
			tarefa.setDescricao(descricao);
			tarefa.setStatusConcluida(false);

			if (opcaoData == 0) {
				String formatoData = "dd/MM/yyyy";
				String dataString = JOptionPane.showInputDialog("Digite uma data no formato dd/MM/yyyy");
				boolean formatoAprovado = ManipulacaoData.verificaFormatoData(dataString, formatoData);

				while (!formatoAprovado) {
					JOptionPane.showMessageDialog(null, "Formato da data incorreto. Tente novamente!");
					dataString = JOptionPane.showInputDialog("Digite uma data no formato dd/MM/yyyy");
					formatoAprovado = ManipulacaoData.verificaFormatoData(dataString, formatoData);
				}
				LocalDate localDate = ManipulacaoData.retornaLocalDate(dataString);
				tarefa.setData(localDate);

			}

			// adicionar metodo aqui

			JOptionPane.showMessageDialog(null, "Tarefa adicionada com sucesso!");
		}

	}

	private void visualizaTarefaPeloId() {
		// TODO Auto-generated method stub

	}

	private void visualizaTarefasNaoConcluidas() {

		String relatorioTarefasNaoConcluidas = TarefaService.geraRelatorioTarefasNaoConlcuidas(tarefas);

		if (relatorioTarefasNaoConcluidas.equals("")) {
			JOptionPane.showMessageDialog(null, "Lista de tarefas nao concluidas vazia.");
		} else {
			JOptionPane.showMessageDialog(null, relatorioTarefasNaoConcluidas);
		}

	}

	private void visualizaTarefasConcluidas() {

		String relatorioTarefasConcluidas = TarefaService.geraRelatorioTarefasConlcuidas(tarefas);

		if (relatorioTarefasConcluidas.equals("")) {
			JOptionPane.showMessageDialog(null, "Lista de tarefas concluidas vazia.");
		} else {
			JOptionPane.showMessageDialog(null, relatorioTarefasConcluidas);
		}

	}

	private void marcaTarefaComoConcluida() {

		String titulo = JOptionPane.showInputDialog("Titulo da tarefa: ");

		Tarefa tarefa = TarefaService.retornaTarefaPeloTitulo(tarefas, titulo);

		if (tarefa == null) {
			JOptionPane.showMessageDialog(null, "Tarefa nao encontrada!");
		} else {

			tarefa.setStatusConcluida(true);
			JOptionPane.showMessageDialog(null, "Tarefa marcada como conluida!");
		}

	}

	private void desmarcaTarefaComoConcluida() {

		String titulo = JOptionPane.showInputDialog("Titulo da tarefa: ");

		Tarefa tarefa = TarefaService.retornaTarefaPeloTitulo(tarefas, titulo);

		if (tarefa == null) {
			JOptionPane.showMessageDialog(null, "Tarefa nao encontrada!");
		} else {

			tarefa.setStatusConcluida(false);
			JOptionPane.showMessageDialog(null, "Tarefa desmarcada como conluida!");

		}

	}

	public void modificaTarefa() {
		Object[] opcoes = { "Titulo", "Descricao", "Data", "Voltar para o menu principal" };

		int opcaoModificar = JOptionPane.showOptionDialog(null, "Deseja modificar qual campo ?", "Modificar",
				JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opcoes, opcoes[0]);

		if (opcaoModificar != ConstantesOpcaoModificarTarefa.VOLTAR) {

			String titulo = JOptionPane.showInputDialog("Titulo da tarefa: ");

			Tarefa tarefaParaModificacao = TarefaService.retornaTarefaNaoConcluidaPeloTitulo(tarefas, titulo);

			if (tarefaParaModificacao != null) {

				if (opcaoModificar == ConstantesOpcaoModificarTarefa.TITULO) {
					String novoTitulo = JOptionPane.showInputDialog("Digite o novo titulo da tarefa: ");
					TarefaService.modificaTituloTarefa(tarefaParaModificacao, novoTitulo);
				} else if (opcaoModificar == ConstantesOpcaoModificarTarefa.DESCRICAO) {
					String novaDescricao = JOptionPane.showInputDialog("Digite a nova descricao da tarefa: ");
					TarefaService.modificaDescricaoTarefa(tarefaParaModificacao, novaDescricao);
				} else if (opcaoModificar == ConstantesOpcaoModificarTarefa.DATA) {
					String formatoData = "dd/MM/yyyy";
					String dataString = JOptionPane.showInputDialog("Digite uma data no formato dd/MM/yyyy");
					boolean formatoAprovado = ManipulacaoData.verificaFormatoData(dataString, formatoData);

					while (!formatoAprovado) {
						JOptionPane.showMessageDialog(null, "Formato da data incorreto. Tente novamente!");
						dataString = JOptionPane.showInputDialog("Digite uma data no formato dd/MM/yyyy");
						formatoAprovado = ManipulacaoData.verificaFormatoData(dataString, formatoData);
					}
					LocalDate localDate = ManipulacaoData.retornaLocalDate(dataString);

					TarefaService.modificaDataTarefa(tarefaParaModificacao, localDate);

				}
				JOptionPane.showMessageDialog(null, "Modificacao feita com sucesso!");

			} else {
				JOptionPane.showMessageDialog(null, "Tarefa nao encontrada para modificacao.");

			}

		}

	}

	private void removeTarefa() {
		String tituloTarefaParaRemover = JOptionPane.showInputDialog("Titulo da tarefa que voce deseja remover: ");

		TarefaService.removeTarefa(tarefas, tituloTarefaParaRemover);
	}

}