package mongoCode;

import com.mongodb.MongoClient;

import java.util.ArrayList;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class Main {

	public static void main(String[] args)throws Exception {
		ArrayList<Libro> listaLibros = new ArrayList<Libro>();
		
		listaLibros.add(new Libro("001", "Matematica", "Profe_1", "Libro_con_Problemas"));
		listaLibros.add(new Libro("002", "Fisica", "Profe_2", "Libro_de_fisica"));
		listaLibros.add(new Libro("003", "DatosII", "Profe_3", "Libro_de_algoritmos"));
		listaLibros.add(new Libro("004", "Circuitos", "Profe_4", "AC_DC"));
		
		try {
		//Conectamos con la base de Datos
		MongoClient mongoClient = new MongoClient("localhost", 27017);
		System.out.println("Se Conecto a la Base De Datos");
		
		//Creamos una base de datos
		DB db = mongoClient.getDB("Libreria");
		
		//Creamos una coleccion
		DBCollection coleccion = db.getCollection("Libros");
		
		//Insetamos elementos
		for (Libro lib : listaLibros) {
			coleccion.insert(lib.toDBObjectLibro());
		}
		
		//Cuantos Libros hay?
		int numDocumentos = (int) coleccion.getCount();
		System.out.println("Número de documentos en la colección Libreria: " + numDocumentos + "\n");
		
		//Buscar Todos los elementos en la BD
		DBCursor cursor = coleccion.find();
		try {
			while (cursor.hasNext()) {
				System.out.println(cursor.next().toString());
			}
		} finally {
			cursor.close();
		}
		
		//Buscar elementos especificos en la BD
		System.out.println("\nLibros de Matematicas Disponibles");
		DBObject query = new BasicDBObject("titulo", new BasicDBObject("$regex", "Matematica"));
		cursor = coleccion.find(query);
		try {
			while (cursor.hasNext()) {
				Libro LirboN = new Libro((BasicDBObject) cursor.next());
				System.out.println(LirboN.toString());
			}
		} finally {
			cursor.close();
		}
		// Borrar todos los libros con id 001 
		DBObject query2 = new BasicDBObject("id", new BasicDBObject("$regex", "001"));
		coleccion.findAndRemove(query2);
		
		
		//Cerrar la conexion
		mongoClient.close();

		
	
		}catch(Exception e) {
			System.out.println("No se conecto a la base de datos");
		}
	}
}
