package main.model.biblioteca;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import main.model.biblioteca.excepciones.LibroException;

public class InfoLibro {
	private String nombre;
	private List<Libro> libros;
	
	public InfoLibro(String nombre, Integer numeroLibreria) {
		this.nombre = nombre;
		this.libros = new ArrayList<>();
		Libro nuevo = new Libro(numeroLibreria);
		this.libros.add(nuevo);
	}

	public String getNombre() {
		return nombre;
	}
	
	/**
	 * Alquila o exicepcion si ya esta alquilado.
	 * @param nombre
	 * @exception LibroException
	 */
	public void alquilar(Credencial alquilador) {
		try {
			Boolean seAlquilo = false;
			for(Libro libro : this.libros) {
				if(!libro.estaAlquilado()) {
					libro.alquilar(alquilador);
					seAlquilo = true;
					break;
				}
			}
			if(!seAlquilo)
				throw new LibroException("El libro ya ha sido alquilado.");
		} catch (LibroException e) {
			throw e;
		} catch (Exception e) {
			throw new LibroException("No se pudo alquilar el libro. "+e.getMessage());
		}
	}
	
	/**
	 * 
	 * @param nombreCliente
	 * @exception LibroException
	 */
	public void devolver(Credencial cliente) {
		try {
			Boolean seDevolvio = false;
			for(Libro libro : this.libros) {
				if(libro.estaAlquilado() && libro.estaAlquiladoPor(cliente)) {
					libro.devolver(cliente);
					seDevolvio = true;
					break;
				}
			}
			if(!seDevolvio)
				throw new LibroException("El libro no ha sido alquilado.");
		} catch (LibroException e) {
			throw e;
		} catch (Exception e) {
			throw new LibroException("No se pudo devolver el libro. "+e.getMessage());
		}
	}
	
	public String agregarLibro(Integer numeroLibreria) {
		Libro nuevo = new Libro(numeroLibreria);
		libros.add(nuevo);
		
		return "Se agrego: "+ nuevo.toString();
	}
	
	public Integer cantidadDeCopias() {
		return libros.size();
	}
	
	public Boolean alquiladoPor(String nombre) {
		Boolean res = false;
		
		for(Libro libro : this.libros) {
			if(libro.estaAlquiladoPor(nombre)) {
				res = true;
				break;
			}
		}
		
		return res;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(nombre);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		InfoLibro other = (InfoLibro) obj;
		return Objects.equals(nombre, other.nombre);
	}

	@Override
	public String toString() {
		return "[nombre=" + nombre + ", Disponibilidad=" + libros + "]";
	}
	
	
	
	
}
