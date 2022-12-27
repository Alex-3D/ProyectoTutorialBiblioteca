package biblioteca;

import java.util.ArrayList;
import java.util.List;

import biblioteca.excepciones.LibroException;

public class Libreria {
	private List<Libro> libros;

	public Libreria() {
		this.libros = new ArrayList<>();
	}

	public Libreria(List<Libro> libros) {
		this.libros = libros;
	}
	
	/**
	 * Agrega el libro nuevo si no esta llena la libreria.
	 * Retorna si pudo agregarlo.
	 * @param nuevoLibro
	 * @return True si pudo agregar el libro. False caso contrario.
	 */
	public Boolean agregarLibro(Libro nuevoLibro) {
		Boolean res = false;
		if(libros.size() < 30) {
			libros.add(nuevoLibro);
			res = true;
		}
		
		return res;
	}
	
	/**
	 * 
	 * @param nombre
	 * @return
	 * @exception LibroException
	 */
	public Boolean contieneLibro(String nombre) {
		try {
			Libro aBuscar = new Libro(nombre);
			return libros.contains(aBuscar);
		} catch (Exception e) {
			throw new LibroException("No se encontro el libro");
		}
	}
	
	/**
	 * 
	 * @param nombreLibro
	 * @param nombreCliente
	 * @return
	 * @exception Exception, LibroException
	 */
	public Libro alquilarLibro(String nombreLibro, String nombreCliente) {
		try {
			Libro aRetornar = new Libro("");
			
			aRetornar = this.buscarLibro(nombreLibro);
			aRetornar.alquilar(nombreCliente);
			
			return aRetornar;
		} catch (LibroException e) {
			throw e;
		} catch (Exception e) {
			throw new LibroException("No se encontro el libro.");
		}
	}
	
	/**
	 * 
	 * @param nombreLibro
	 * @param nombreCliente
	 * @return
	 * @exception Exception, LibroException
	 */
	public Libro buscarLibro(String nombreLibro) {
		try {
			Libro aRetornar = new Libro("");
			Libro aBuscar = new Libro(nombreLibro);
			
			if (this.contieneLibro(nombreLibro)) {
				aRetornar = this.libros.get(this.libros.indexOf(aBuscar));
			}
			
			return aRetornar;
		} catch (LibroException e) {
			throw e;
		} catch (Exception e) {
			throw new LibroException("No se encontro el libro.");
		}
	}
	
	/**
	 * 
	 * @param nombreLibro
	 * @param nombreCliente
	 * @return
	 * @exception Exception, LibroException
	 */
	public String devolverLibro(String nombreLibro, String nombreCredencial) {
		try {
			Libro aDevolver = new Libro("");
			
			aDevolver = this.buscarLibro(nombreLibro);
			aDevolver.devolver(nombreCredencial);
			
			return "El "+ nombreLibro + " libro ha sido devuelto.";
		} catch (LibroException e) {
			throw e;
		} catch (Exception e) {
			throw new LibroException("No se encontro el libro.");
		}
			
	}

	@Override
	public String toString() {
		return "Libreria [libros=" + libros + "]";
	}
	
	
}
