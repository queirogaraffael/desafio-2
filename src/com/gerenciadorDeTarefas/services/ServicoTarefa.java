package com.gerenciadorDeTarefas.services;

import java.util.Set;

import com.gerenciadorDeTarefas.entities.Tarefa;

public class ServicoTarefa {
	// metodo que adiciona tarefas
	public static void adicionarTarefa(Set<Tarefa> tarefas, String tarefa) {
		tarefas.add(new Tarefa(tarefa));
	}

	// metodo que visualiza as tarefas
	public static void visualizarTarefa(Set<Tarefa> tarefas) {
		for (Tarefa t : tarefas) {
			System.out.println(t);

		}
	}

	// metodo que remove uma tarefa
	public static void removerTarefa(Set<Tarefa> tarefas, String obj) {
		tarefas.removeIf(p -> p.getTarefa().equals(obj));
	}

	// metodo que verifica se a tarefa já foi registrada no gerenciador de tarefas
	// streamei a lista de tarefas para não precisar percorrer explicitamente
	// através de um for
	public static boolean jaContemTarefa(Set<Tarefa> tarefas, String tarefaParaAdicionar) {
		boolean tarefaJaExiste = tarefas.stream().anyMatch(p -> p.getTarefa().equals(tarefaParaAdicionar));
		return tarefaJaExiste;

	}

}
