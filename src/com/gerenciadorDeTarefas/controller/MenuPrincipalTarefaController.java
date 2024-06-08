package com.gerenciadorDeTarefas.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.JOptionPane;

import com.gerenciadorDeTarefas.MongoDB.MongoDBConnection;
import com.gerenciadorDeTarefas.commons.constantes.ConstantesMenuPrincipal;
import com.gerenciadorDeTarefas.commons.constantes.ConstantesOpcaoModificarTarefa;
import com.gerenciadorDeTarefas.commons.util.ComparadorData;
import com.gerenciadorDeTarefas.commons.util.GeraRelatorioTarefas;
import com.gerenciadorDeTarefas.commons.util.VerificaFormatoData;
import com.gerenciadorDeTarefas.model.dao.DaoFactory;
import com.gerenciadorDeTarefas.model.dao.TarefaDao;
import com.gerenciadorDeTarefas.model.entities.Tarefa;
import com.gerenciadorDeTarefas.view.GerenciadorTarefasView;

public class MenuPrincipalTarefaController {

	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	private TarefaDao tarefaDao;

	public MenuPrincipalTarefaController() throws Exception {
		this.tarefaDao = DaoFactory.createMedicoDao();

	}

	public void exibiMenuPrincipal() {

		String opcaoMenuPrincipal = "";

		do {

			try {
				opcaoMenuPrincipal = GerenciadorTarefasView.menuPrincipalView();

				switch (opcaoMenuPrincipal) {

				case (ConstantesMenuPrincipal.ADICIONAR):
					adicionaTarefa();
					break;

				case (ConstantesMenuPrincipal.VISUALIZA_ID_TITULO_DATA_STATUS):
					visualizaIdTituloDataStatusTarefas();
					break;

				case (ConstantesMenuPrincipal.VISUALIZAR_TAREFA_ID):
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

				case (ConstantesMenuPrincipal.REMOVER):
					removeTarefa();
					break;

				case (ConstantesMenuPrincipal.SAIR):
					MongoDBConnection.close();

				}

			} catch (Exception erro) {
				JOptionPane.showMessageDialog(null, "Erro: " + erro.getMessage());
			}

		} while (!opcaoMenuPrincipal.equals(ConstantesMenuPrincipal.SAIR));

	}

	private void adicionaTarefa() throws Exception {

		String idTarefa = JOptionPane.showInputDialog("Digite o ID da tarefa: ");

		if (tarefaDao.verificaSeJaTemId(idTarefa)) {
			JOptionPane.showMessageDialog(null, "Id ja cadastrado. Tente com outro!");
		} else {

			String titulo = JOptionPane.showInputDialog("Titulo da tarefa: ");
			String descricao = JOptionPane.showInputDialog("Descricao da tarefa: ");

			Object[] opcoes = { "Sim", "Nao" };

			int opcaoData = JOptionPane.showOptionDialog(null, "Deseja adicionar uma data especifica ?", "Data",
					JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opcoes, opcoes[0]);

			Tarefa tarefa = new Tarefa();
			tarefa.setTitulo(titulo);
			tarefa.setDescricao(descricao);
			tarefa.setStatus(false);

			if (opcaoData == 0) {
				String formatoData = "dd/MM/yyyy";
				String dataString = JOptionPane.showInputDialog("Digite uma data no formato dd/MM/yyyy");
				boolean formatoAprovado = VerificaFormatoData.verificaFormatoData(dataString, formatoData);

				while (!formatoAprovado) {
					JOptionPane.showMessageDialog(null, "Formato da data incorreto. Tente novamente!");
					dataString = JOptionPane.showInputDialog("Digite uma data no formato dd/MM/yyyy");
					formatoAprovado = VerificaFormatoData.verificaFormatoData(dataString, formatoData);
				}
				tarefa.setData(dataString);

			} else {
				tarefa.setData(LocalDate.now().format(formatter));
			}

			tarefaDao.insereTarefa(idTarefa, tarefa);

			JOptionPane.showMessageDialog(null, "Tarefa adicionada com sucesso!");

		}
	}

	private void visualizaIdTituloDataStatusTarefas() throws Exception {

		String relatorio = tarefaDao.retornaIdTituloStatusDataTarefas();

		JOptionPane.showMessageDialog(null, relatorio);

	}

	private void visualizaTarefaPeloId() throws Exception {

		String idTarefa = JOptionPane.showInputDialog("Digite o ID da tarefa: ");

		Tarefa tarefa = tarefaDao.retornaTarefaPeloId(idTarefa);

		if (tarefa == null) {
			JOptionPane.showMessageDialog(null, "Tarefa nao encontrada!");
		} else {

			JOptionPane.showMessageDialog(null, tarefa);

		}

	}

	private void visualizaTarefasNaoConcluidas() throws Exception {

		List<Tarefa> tarefasNaoConcluidas = tarefaDao.retornaTarefasNaoConcluidas();

		if (tarefasNaoConcluidas.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Lista de tarefas nao concluidas vazia.");
		} else {

			tarefasNaoConcluidas.sort(new ComparadorData());

			String relatorioTarefasNaoConcluidas = GeraRelatorioTarefas.geraRelatorioTarefas(tarefasNaoConcluidas);

			JOptionPane.showMessageDialog(null, relatorioTarefasNaoConcluidas);
		}

	}

	private void visualizaTarefasConcluidas() throws Exception {

		List<Tarefa> tarefasConcluidas = tarefaDao.retornaTarefasConcluidas();

		if (tarefasConcluidas.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Lista de tarefas concluidas vazia.");
		} else {
			tarefasConcluidas.sort(new ComparadorData());

			String relatorioTarefasConcluidas = GeraRelatorioTarefas.geraRelatorioTarefas(tarefasConcluidas);

			JOptionPane.showMessageDialog(null, relatorioTarefasConcluidas);
		}

	}

	private void marcaTarefaComoConcluida() throws Exception {

		String idTarefa = JOptionPane.showInputDialog("Digite o ID da tarefa: ");

		Tarefa tarefa = tarefaDao.retornaTarefaPeloId(idTarefa);

		if (tarefa == null) {
			JOptionPane.showMessageDialog(null, "Tarefa nao encontrada!");
		} else {

			tarefaDao.marcaTarefaComoConcluidaPeloId(idTarefa);
			JOptionPane.showMessageDialog(null, "Tarefa marcada como conluida!");

		}

	}

	private void desmarcaTarefaComoConcluida() throws Exception {

		String idTarefa = JOptionPane.showInputDialog("Digite o ID da tarefa: ");

		Tarefa tarefa = tarefaDao.retornaTarefaPeloId(idTarefa);

		if (tarefa == null) {
			JOptionPane.showMessageDialog(null, "Tarefa nao encontrada!");
		} else {

			tarefaDao.dermarcaTarefaComoConcluidaPeloId(idTarefa);
			JOptionPane.showMessageDialog(null, "Tarefa desmarcada como conluida!");

		}

	}

	public void modificaTarefa() throws Exception {
		Object[] opcoes = { "Descricao", "Data", "Voltar para o menu principal" };

		int opcaoModificar = JOptionPane.showOptionDialog(null, "Deseja modificar qual campo ?", "Modificar",
				JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opcoes, opcoes[0]);

		if (opcaoModificar != ConstantesOpcaoModificarTarefa.VOLTAR) {

			String idTarefa = JOptionPane.showInputDialog("Digite o ID da tarefa: ");

			Tarefa tarefaParaModificacao = tarefaDao.retornaTarefaPeloId(idTarefa);

			if (tarefaParaModificacao != null) {

				if (opcaoModificar == ConstantesOpcaoModificarTarefa.DESCRICAO) {

					String novaDescricao = JOptionPane.showInputDialog("Digite a nova descricao da tarefa: ");
					tarefaDao.modificaDescricaoTarefaPeloId(tarefaParaModificacao, novaDescricao);

				} else if (opcaoModificar == ConstantesOpcaoModificarTarefa.DATA) {
					String formatoData = "dd/MM/yyyy";
					String dataString = JOptionPane.showInputDialog("Digite uma data no formato dd/MM/yyyy");
					boolean formatoAprovado = VerificaFormatoData.verificaFormatoData(dataString, formatoData);

					while (!formatoAprovado) {
						JOptionPane.showMessageDialog(null, "Formato da data incorreto. Tente novamente!");
						dataString = JOptionPane.showInputDialog("Digite uma data no formato dd/MM/yyyy");
						formatoAprovado = VerificaFormatoData.verificaFormatoData(dataString, formatoData);
					}

					tarefaDao.modificaDataTarefaPeloId(tarefaParaModificacao, dataString);

				}
				JOptionPane.showMessageDialog(null, "Modificacao feita com sucesso!");

			} else {
				JOptionPane.showMessageDialog(null, "Tarefa nao encontrada para modificacao.");

			}

		}

	}

	private void removeTarefa() throws Exception {
		String idTarefaParaRemover = JOptionPane.showInputDialog("Digite o ID da tarefa que voce deseja remover: ");

		tarefaDao.deleteTarefa(idTarefaParaRemover);

		JOptionPane.showMessageDialog(null, "Tarefa removida com sucesso!");
	}

}