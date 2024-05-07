package com.gerenciadorDeTarefas.services;

import java.util.Set;

import com.gerenciadorDeTarefas.entities.Tarefa;

public class TarefaService {

	public static String geraRelatorioTarefasNaoConlcuidas(Set<Tarefa> tarefas) {

		StringBuilder sb = new StringBuilder();

		for (Tarefa tarefa : tarefas) {
			if (tarefa.getStatusConcluida() == false) {
				sb.append(tarefa).append("\n----------------\n");
			}
		}

		return sb.toString();
	}

	public static String geraRelatorioTarefasConlcuidas(Set<Tarefa> tarefas) {

		StringBuilder sb = new StringBuilder();

		for (Tarefa tarefa : tarefas) {
			if (tarefa.getStatusConcluida() == true) {
				sb.append(tarefa).append("\n----------------\n");
			}
		}

		return sb.toString();
	}

	public static void removeTarefa(Set<Tarefa> tarefas, String tituloTarefaParaRemover) {
		tarefas.removeIf(p -> p.getTitulo().equals(tituloTarefaParaRemover));
	}

	public static boolean verificaSeJaExisteTarefaPeloTitulo(Set<Tarefa> tarefas, String tituloTarefa) {

		for (Tarefa tarefa : tarefas) {
			if (tarefa.getTitulo().equals(tituloTarefa)) {
				return true;
			}
		}
		return false;
	}

	public static Tarefa retornaTarefaPeloTitulo(Set<Tarefa> tarefas, String tituloTarefa) {

		for (Tarefa tarefa : tarefas) {
			if (tarefa.getTitulo().equals(tituloTarefa)) {
				return tarefa;
			}
		}

		return null;

	}

}
