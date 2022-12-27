package biblioteca;

import biblioteca.excepciones.CredencialException;
import biblioteca.excepciones.LibroException;

public class Libro {
	private Integer numeroLibrero;
	private Boolean alquilado;
	private Credencial alquiladoPor;
	
	public Libro(Integer numeroLibrero) {
		this.numeroLibrero = numeroLibrero;
		this.alquilado = false;
	}
	
	public Boolean estaAlquilado() {
		return this.alquilado;
	}
	
	public void alquilar(Credencial alquilador ) {
		if(!this.alquilado) {
			this.alquilado = true;
			this.alquiladoPor = alquilador;
		} else {
			throw new LibroException("El libro ya ha sido alquilado.");
		}
	}
	
	public Boolean estaAlquiladoPor(String cliente) {
		boolean res = false;
		Credencial credencialCliente = new Credencial(cliente);
		
		if(this.estaAlquiladoPor(credencialCliente))
			res = true;
		
		return res;
	}
	
	/**
	 * 
	 * @param cliente
	 * @return
	 * @exception <code>false</code>
	 */
	public Boolean estaAlquiladoPor(Credencial cliente) {
		try {
			boolean res = false;
			
			if(this.getAlquiladoPor().equals(cliente))
				res = true;
			
			return res;
		} catch (Exception e) {
			return false;
		}
	}
	
	public Credencial getAlquiladoPor() {
		return this.alquiladoPor;
	}
	
	/**
	 * 
	 * @param cliente
	 * @exception LibroException
	 */
	public void devolver(Credencial cliente) {
		if(this.alquilado && this.estaAlquiladoPor(cliente)) {
			this.alquilado = false;
			this.alquiladoPor = null;
		} else {
			throw new LibroException("El libro no ha sido alquilado.");
		}
	}

	@Override
	public String toString() {
		String res = "";
		if(this.alquilado) {
			res = "[numeroLibrero=" + numeroLibrero + ", alquilado=" + alquilado + ", alquilado Por= " + alquiladoPor.getNombre()
			+ "]";
		} else {
			res = "[numeroLibrero=" + numeroLibrero + ", alquilado=" + alquilado + ", alquilado Por= none"
			+ "]";
		}
		
		
		return res;
	}
	
}
