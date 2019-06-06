package Server;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import mongoCode.MongoResourse;
import mongoCode.Libro;

public class Tienda {

	private static Tienda INSTANCE = new Tienda();
	private static MongoResourse mongo = new MongoResourse(); 
	
	private Tienda() {
		Libros.put("123", new Libro("001", "Matematica", "Profe_1", "Libro_con_Problemas"));
		Libros.put("456", new Libro("002", "Fisica", "Profe_2", "Libro_de_fisica"));
		Libros.put("789", new Libro("003", "DatosII", "Profe_3", "Libro_de_algoritmos"));
	}
	
	public static Tienda obtenerTienda() {
		return INSTANCE;
	}
	
	private Map<String,Libro> Libros = new HashMap<>();
	
	public Collection<Libro> VerTodosLibros() {
		return mongo.VerTodosLibros();
	}
	
	public Libro obtenerLibro(String Id) {
		return mongo.obtenerLibro(Id);
	}
	
	public void guardarLibro(Libro nuevoLibro) {
		mongo.guardarLibro(nuevoLibro);
	}
	
	public void renovarLibro(String Id, Libro libroARenovar) {
		mongo.renovarLibro(Id, libroARenovar);
	}
	public void borrarLibro(String Id) {
		mongo.borrarLibro(Id);
	}
}

