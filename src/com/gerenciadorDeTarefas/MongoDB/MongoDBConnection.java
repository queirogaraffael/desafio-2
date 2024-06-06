package com.gerenciadorDeTarefas.MongoDB;

import com.mongodb.ConnectionString;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

public class MongoDBConnection {
	private static final String connectionString = "mongodb://localhost:27017";
	private static MongoClient mongoClient;

	public static MongoClient getMongoClient() {
		if (mongoClient == null) {
			mongoClient = MongoClients.create(new ConnectionString(connectionString));
		}
		return mongoClient;
	}

	public static void close() {
		if (mongoClient != null) {
			mongoClient.close();
		}
	}

}
