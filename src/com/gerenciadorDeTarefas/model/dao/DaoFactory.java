package com.gerenciadorDeTarefas.model.dao;

import com.gerenciadorDeTarefas.MongoDB.MongoDBConnection;
import com.gerenciadorDeTarefas.model.dao.imp.TarefaDaoMongoDB;

public class DaoFactory {

	public static TarefaDao createMedicoDao() throws Exception {

		return new TarefaDaoMongoDB(MongoDBConnection.getMongoClient());

	}

}
