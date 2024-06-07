package com.gerenciadorDeTarefas.model.dao.imp;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.gerenciadorDeTarefas.model.dao.TarefaDao;
import com.gerenciadorDeTarefas.model.entities.Tarefa;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

public class TarefaDaoMongoDB implements TarefaDao {

	private final MongoCollection<Document> collection;

	public TarefaDaoMongoDB(MongoClient mongoClient) throws Exception {
		MongoDatabase database = mongoClient.getDatabase("app_tarefas");
		this.collection = database.getCollection("tarefas");
	}

	@Override
	public void insereTarefa(String id, Tarefa tarefa) throws Exception {
		Document doc = new Document("_id", id).append("titulo", tarefa.getTitulo())
				.append("descricao", tarefa.getDescricao()).append("data", tarefa.getData().toString())
				.append("statusConcluida", tarefa.getStatusConcluida());
		collection.insertOne(doc);
	}

	@Override
	public String retornaIdTituloStatusDataTarefas() throws Exception {
		String dados = "";

		for (Document doc : collection.find()) {
			String id = doc.getString("_id");
			String titulo = doc.getString("titulo");
			LocalDate data = LocalDate.parse(doc.getString("data"));
			String status;

			if (doc.getBoolean("statusConcluida") == true) {
				status = "concluida";
			} else {
				status = "nao concluida";
			}

			StringBuilder sb = new StringBuilder();
			sb.append("Id: " + id + ", Titulo: " + titulo + ", Data: " + data.toString() + ", Status: " + status);

			dados += sb.toString() + "\n";

		}
		return dados;

	}

	@Override
	public Tarefa retornaTarefaPeloId(String id) throws Exception {

		for (Document doc : collection.find()) {

			if (doc.getString("_id").equals(id)) {

				Tarefa tarefa = new Tarefa(doc.getString("_id"), doc.getString("titulo"), doc.getString("descricao"),
						LocalDate.parse(doc.getString("data")), doc.getBoolean("statusConcluida"));

				return tarefa;
			}
		}

		return null;
	}

	@Override
	public List<Tarefa> retornaTarefasNaoConcluidas() throws Exception {
		List<Tarefa> tarefas = new ArrayList<>();
		for (Document doc : collection.find()) {

			if (doc.getBoolean("statusConcluida") == false) {

				tarefas.add(new Tarefa(doc.getString("_id"), doc.getString("titulo"), doc.getString("descricao"),
						LocalDate.parse(doc.getString("data")), doc.getBoolean("statusConcluida")));
			}
		}

		return tarefas;
	}

	@Override
	public List<Tarefa> retornaTarefasConcluidas() throws Exception {
		List<Tarefa> tarefas = new ArrayList<>();
		for (Document doc : collection.find()) {

			if (doc.getBoolean("statusConcluida") == true) {
				new Tarefa(doc.getString("_id"), doc.getString("titulo"), doc.getString("descricao"),
						LocalDate.parse(doc.getString("data")), doc.getBoolean("statusConcluida"));
			}

		}
		return tarefas;
	}

	@Override
	public void marcaTarefaComoConcluidaPeloId(String id) throws Exception {
		Document filterDocument = new Document("_id", id);
		Document updateDoc = new Document("$set", new Document("statusConcluida", true));
		collection.updateOne(filterDocument, updateDoc);

	}

	@Override
	public void dermarcaTarefaComoConcluidaPeloId(String id) throws Exception {
		Document filterDocument = new Document("_id", id);
		Document updateDoc = new Document("$set", new Document("statusConcluida", false));
		collection.updateOne(filterDocument, updateDoc);

	}

	@Override
	public void modificaDescricaoTarefaPeloId(Tarefa tarefa, String novaDescricao) throws Exception {
		Document filterDocument = new Document("_id", tarefa.getId());
		Document updateDoc = new Document("$set", new Document("descricao", novaDescricao));
		collection.updateOne(filterDocument, updateDoc);
	}

	@Override
	public void modificaDataTarefaPeloId(Tarefa tarefa, String novaData) throws Exception {

		Document filterDocument = new Document("_id", tarefa.getId());
		Document updateDoc = new Document("$set", new Document("data", novaData));
		collection.updateOne(filterDocument, updateDoc);

	}

	@Override
	public void deleteTarefa(String id) throws Exception {
		Document filterDocument = new Document("_id", id);
		collection.deleteOne(filterDocument);

	}

	@Override
	public Long retornaNumeroDeTarefas() throws Exception {
		return collection.countDocuments();
	}

	@Override
	public boolean verificaSeJaTemId(String id) throws Exception {
		Document existingDoc = collection.find(Filters.eq("_id", id)).first();
		return existingDoc != null;
	}

}
