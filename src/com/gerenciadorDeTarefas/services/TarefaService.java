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
	
	

}
