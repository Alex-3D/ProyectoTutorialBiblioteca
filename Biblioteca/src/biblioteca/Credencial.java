package biblioteca;

import java.util.List;
import java.util.Objects;

public class Credencial {
	private Integer id;
	private String nombre;
	private Boolean habilitado;
	private Boolean admin;
	private List<Libro> libros;
	
	public Credencial(Integer id, String nombre) {
		this.id = id;
		this.nombre = nombre;
		this.habilitado = true;
		this.admin = false;
	}
	
	public Credencial(Integer id, String nombre, Boolean esAdmin) {
		this.id = id;
		this.nombre = nombre;
		this.habilitado = true;
		this.admin = esAdmin;
	}

	public Integer getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}
	
	public void habilitar() {
		this.habilitado = true;
	}
	
	public void deshabilitar() {
		this.habilitado = false;
	}
	
	public Boolean estaHabilitado() {
		return this.habilitado;
	}
	
	public Boolean esAdmin() {
		return admin;
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
		Credencial other = (Credencial) obj;
		return Objects.equals(nombre, other.nombre);
	}

	@Override
	public String toString() {
		return "Credencial [nombre=" + nombre + ", habilitado=" + habilitado + ", admin=" + admin + "]";
	}
	
}
