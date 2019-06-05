package mongoCode;

import com.mongodb.MongoClient;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class MongoResourse {
	
	
	private Map<String,Libro> ListaLibros = new HashMap<>();
	private DBCollection coleccion;
	private DBCursor cursor;
	
	public MongoResourse() {
		try {
		MongoClient mongoClient = new MongoClient("localhost", 27017);
		DB db = mongoClient.getDB("Libreria");
		coleccion = db.getCollection("Libros");
		}catch(Exception e) {
			System.out.println("No se conecto a la base de datos");
		}
	}
	
	public Collection<Libro> VerTodosLibros() {
		ListaLibros = new HashMap<>();
		cursor = coleccion.find();
		try {
			while (cursor.hasNext()) {
				Libro L = new Libro((BasicDBObject) cursor.next());
				ListaLibros.put(L.getId(),L);
			}
		} finally {
			cursor.close();
		}
		return ListaLibros.values();
	}
	
	public Libro obtenerLibro(String Id) {
		DBObject query = new BasicDBObject("id", new BasicDBObject("$regex", Id));
		cursor = coleccion.find(query);
		Libro LirboN = null;
		try {
			while (cursor.hasNext()) {
				LirboN = new Libro((BasicDBObject) cursor.next());
			}
		} finally {
			cursor.close();
		}
		coleccion.findAndRemove(query);
		return LirboN;
	}
	
	public void guardarLibro(Libro nuevoLibro) {
		coleccion.insert(nuevoLibro.toDBObjectLibro());
	}
	
	public void renovarLibro(String Id, Libro lR) {
		DBObject query = new BasicDBObject("id", new BasicDBObject("$regex", Id));
		coleccion.findAndRemove(query);
		Libro n = new Libro(Id,lR.getTitle(),lR.getAuthor(),lR.getDescription());
		coleccion.insert(n.toDBObjectLibro());
	}

}
