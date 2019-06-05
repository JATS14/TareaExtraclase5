package mongoCode;

import com.mongodb.BasicDBObject;

public class Libro {
	
	private String id;
	private String titulo;
	private String autor;
	private String descripcion;
	
	public Libro() {}
	
	public Libro (String id, String title, String author, String description) {
		this.id = id;
		this.titulo = title;
		this.autor = author;
		this.descripcion = description;
	}
	public BasicDBObject toDBObjectLibro() {
		BasicDBObject dBObjectLibro = new BasicDBObject();
		dBObjectLibro.append("id", this.getId());
		dBObjectLibro.append("titulo", this.getTitle());
		dBObjectLibro.append("autor", this.getAuthor());
		dBObjectLibro.append("descripcion", this.getDescription());

		return dBObjectLibro;
	}
	public Libro(BasicDBObject toDBObjectLibro) {
		this.id = toDBObjectLibro.getString("id");
		this.titulo = toDBObjectLibro.getString("titulo");
		this.autor = toDBObjectLibro.getString("autor");
		this.descripcion = toDBObjectLibro.getString("descripcion");
	}
	
	public String getidObject(BasicDBObject toDBObjectLibro) {
		String id2 = toDBObjectLibro.getString("id");
		return id2;
	}
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return titulo;
	}
	public void setTitle(String title) {
		this.titulo = title;
	}
	public String getAuthor() {
		return autor;
	}
	public void setAuthor(String author) {
		this.autor = author;
	}
	public String getDescription() {
		return descripcion;
	}
	public void setDescription(String description) {
		this.descripcion = description;
	}
	@Override
	public String toString() {
		return "id: " + this.getId() + " / titulo: " + this.titulo + " / autor: " + this.autor + " / descripcion: " + this.descripcion;
	}

}
