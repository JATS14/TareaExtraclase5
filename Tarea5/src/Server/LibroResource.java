package Server;

import java.util.Collection;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import mongoCode.Libro;

@Path("/libros")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class LibroResource {

	@GET
	public Collection<Libro> listaLibros() {
		return Tienda.obtenerTienda().VerTodosLibros();
	}
	
	@Path("/{id}")
	@DELETE
	public Libro obtenerLibro(@PathParam("id") String Id) {
		return Tienda.obtenerTienda().obtenerLibro(Id);
	}
	
	@POST
	public void guardarLibro(Libro nuevoLibro) {
		Tienda.obtenerTienda().guardarLibro(nuevoLibro);
	}
	
	@Path("/{id}")
	@PUT
	public void renovarLibro(@PathParam("id") String Id, Libro LibroN) {
		Tienda.obtenerTienda().renovarLibro(Id, LibroN);
	}
}

