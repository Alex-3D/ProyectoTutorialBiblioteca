package biblioteca;

import java.util.Objects;

import biblioteca.excepciones.LibroException;

public class Libro {
	private String nombre;
	private Boolean alquilado;
	private String alquiladoPor;
	
	public Libro(String nombre) {
		this.nombre = nombre;
		this.alquilado = false;
		this.alquiladoPor = "";
	}
	public Boolean getAlquilado() {
		return alquilado;
	}
	public String getAlquiladoPor() {
		return alquiladoPor;
	}
	public String getNombre() {
		return nombre;
	}
	
	/**
	 * Alquila o exicepcion si ya esta alquilado.
	 * @param nombre
	 * @exception LibroException
	 */
	public void alquilar(String nombre) {
		if(!this.alquilado) {
			this.alquilado = true;
			this.alquiladoPor = nombre;
		} else {
			throw new LibroException("El libro ya ha sido alquilado.");
		}
	}
	
	/**
	 * 
	 * @param nombreCliente
	 * @exception LibroException
	 */
	public void devolver(String nombreCliente) {
		if(this.alquilado && this.estaAlquiladoPor(nombreCliente)) {
			this.alquilado = false;
			this.alquiladoPor = "";
		} else {
			throw new LibroException("El libro no ha sido alquilado.");
		}
	}
	
	private Boolean estaAlquiladoPor(String nombreCliente) {
		boolean res = false;
		
		if(this.getAlquiladoPor().equals(nombreCliente))
			res = true;
		
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
		Libro other = (Libro) obj;
		return Objects.equals(nombre, other.nombre);
	}
	@Override
	public String toString() {
		return "Libro [nombre=" + nombre + ", alquilado=" + alquilado + ", alquiladoPor=" + alquiladoPor + "]";
	}
	
	
}
