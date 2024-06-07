package com.gerenciadorDeTarefas.commons.util;

import java.util.List;

import com.gerenciadorDeTarefas.model.entities.Tarefa;

public class GeraRelatorioTarefas {

	public static String geraRelatorioTarefas(List<Tarefa> tarefas) {

		StringBuilder sb = new StringBuilder();

		for (Tarefa tarefa : tarefas) {
			sb.append(tarefa).append("\n----------------\n");
		}
		return sb.toString();
	}

}
