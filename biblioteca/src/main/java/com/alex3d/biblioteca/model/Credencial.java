package com.alex3d.biblioteca.model;

import java.util.Objects;

public class Credencial {
	private String nombre;
	private Boolean habilitado;
	private Boolean admin;
	
	public Credencial(String nombre) {
		this.nombre = nombre;
		this.habilitado = true;
		this.admin = false;
	}
	
	public Credencial(String nombre, Boolean esAdmin) {
		this.nombre = nombre;
		this.habilitado = true;
		this.admin = esAdmin;
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
		return "[nombre=" + nombre + ", habilitado=" + habilitado + ", admin=" + admin + "]";
	}
	
}
