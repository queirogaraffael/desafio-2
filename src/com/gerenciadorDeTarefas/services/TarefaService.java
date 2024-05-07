package com.gerenciadorDeTarefas.services;

import java.util.Set;

import com.gerenciadorDeTarefas.entities.Tarefa;

public class TarefaService {

	public static String geraRelatorioTarefas(Set<Tarefa> tarefas) {

		StringBuilder sb = new StringBuilder();

		for (Tarefa tarefa : tarefas) {
			sb.append(tarefa).append("\n----------------\n");
		}

		return sb.toString();
	}

}
