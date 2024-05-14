package MongoDB;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.gerenciadorDeTarefas.entities.Tarefa;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class TarefaDAO {
	private static final String DATABASE_NAME = "app_tarefas";
	private static final String COLLECTION_NAME = "tarefas";

	public static void inserirTarefa(Tarefa tarefa) {
		MongoDatabase database = MongoDBConnection.getMongoClient().getDatabase(DATABASE_NAME);
		MongoCollection<Document> collection = database.getCollection(COLLECTION_NAME);

		Document document = new Document("titulo", tarefa.getTitulo()).append("descricao", tarefa.getDescricao())
				.append("data", tarefa.getData()).append("statusConcluida", tarefa.getStatusConcluida());
// botar na entidade o hashcode a partir disso
		if (tarefa.getId() == null) {
			ObjectId id = new ObjectId();
			tarefa.setId(id);
			document.append("_id", id);
		} else {
			document.append("_id", tarefa.getId());
		}

		collection.insertOne(document);

	}
	
	
	// metodo de criar
	// metodo de remover (pelo id)
	// metodo de visualizar (por demanda talvez ?)
	

}
