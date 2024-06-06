package com.gerenciadorDeTarefas.model.dao;

import java.util.List;

import com.gerenciadorDeTarefas.model.entities.Tarefa;

public interface TarefaDao {

	void insereTarefa(Tarefa tarefa);

	Tarefa retornaTarefaPeloId(String id);

	List<Tarefa> retornaTarefasNaoConcluidas();

	List<Tarefa> retornaTarefasConcluidas();

	void marcaTarefaComoConcluidaPeloId(String id);

	void dermarcaTarefaComoConcluidaPeloId(String id);

	void modificaDescricaoTarefaPeloId(String id);

	void modificaDataTarefaPeloId(String id);

	void deleteTarefa(String id);

}
