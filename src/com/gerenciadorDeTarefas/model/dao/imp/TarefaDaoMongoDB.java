package com.gerenciadorDeTarefas.model.dao.imp;

import java.util.List;

import org.bson.Document;

import com.gerenciadorDeTarefas.model.dao.TarefaDao;
import com.gerenciadorDeTarefas.model.entities.Tarefa;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class TarefaDaoMongoDB implements TarefaDao {

	private final MongoCollection<Document> collection;

	public TarefaDaoMongoDB(MongoClient mongoClient) {
		MongoDatabase database = mongoClient.getDatabase("app_tarefas");
		this.collection = database.getCollection("tarefas");
	}

	@Override
	public void insereTarefa(Tarefa tarefa) {
		// TODO Auto-generated method stub

	}

	@Override
	public Tarefa retornaTarefaPeloId(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Tarefa> retornaTarefasNaoConcluidas() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Tarefa> retornaTarefasConcluidas() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void marcaTarefaComoConcluidaPeloId(String id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void dermarcaTarefaComoConcluidaPeloId(String id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void modificaDescricaoTarefaPeloId(String id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void modificaDataTarefaPeloId(String id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteTarefa(String id) {
		// TODO Auto-generated method stub

	}

}
