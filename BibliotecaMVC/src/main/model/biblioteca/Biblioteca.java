package main.model.biblioteca;

import java.util.ArrayList;
import java.util.List;

import main.model.biblioteca.Biblioteca;
import main.model.biblioteca.excepciones.CredencialException;
import main.model.biblioteca.excepciones.LibroException;

public class Biblioteca {
	private List<InfoLibro> libros;
	private List<Credencial> credenciales;
	private Credencial usuarioActual;
	
	
	
	public Biblioteca() {
		this.libros = new ArrayList<>();
		this.credenciales = new ArrayList<>();
	}
	
	public Biblioteca(List<InfoLibro> libros) {
		this.libros = libros;
		this.credenciales = new ArrayList<>();
	}

	public Biblioteca(List<InfoLibro> libros, List<Credencial> credenciales) {
		this.libros = libros;
		this.credenciales = credenciales;
	}

	/**
	 * Requiere ser Admin.
	 * @param nuevo
	 * @exception LibroException, CredencialException
	 */
	public String agregarLibro(String nuevo, Integer numeroLibreria) {
		try {
			InfoLibro agregado = null;
			InfoLibro encontrado;
			Boolean seAgrego = false;
			
			if(!this.validarAdmin(this.usuarioActual.getNombre()))
				throw new CredencialException("Se necesita permiso de Admin para realizar esta operacion.");
			
			try {
				encontrado = this.buscarLibro(nuevo);
				encontrado.agregarLibro(numeroLibreria);
				agregado = encontrado;
				seAgrego = true;
			} catch (LibroException e) { }
			
			if(!seAgrego) {
				agregado = this.agregarNuevoInfoLibro(nuevo, numeroLibreria);
			}
			
			return "Se agrego el libro" + agregado.toString();
				
		} catch (LibroException e) {
			throw e;
		} catch (Exception e) {
			throw new LibroException("No se pudo agregar el libro: "+nuevo.toString()+" devido a: "+ e.getMessage());
		}
	}
	
	private InfoLibro agregarNuevoInfoLibro(String nombre, Integer numeroLibreria) {
		InfoLibro nuevaInfo = new InfoLibro(nombre, numeroLibreria);
		libros.add(nuevaInfo);
		return nuevaInfo;
	}
	
	/**
	 * 
	 * @param nombreLibro
	 * @param nombreCredencial
	 * @return
	 * @exception CredencialException, LibroException
	 */
	public String alquilarLibro(String nombreLibro, String nombreCredencial) {
		try {
			InfoLibro libroEncontrado = null;
			Credencial cliente = null;
			
			cliente = this.buscarCredencial(nombreCredencial);
			if(!cliente.estaHabilitado())
				throw new CredencialException("Credencial inhabilitada");
			
			libroEncontrado = this.buscarLibro(nombreLibro);
			libroEncontrado.alquilar(cliente);
			
			return "Se alquilo el libro" + libroEncontrado.toString();
		} catch (CredencialException e) {
			throw e;
		} catch (LibroException e) {
			throw e;
		} catch (Exception e) {
			throw new LibroException("No se pudo encontrar el libro, devido a: "+e.getMessage());
		}
	}
	
	/**
	 * El cliente devuelve el libro a la biblioteca.
	 * @param nombreLibro
	 * @param nombreCredencial
	 * @return
	 * @exception CredencialException,LibroException
	 */
	public String devolverLibro(String nombreLibro, String nombreCredencial) {
		try {
			InfoLibro libroEncontrado = null;
			Credencial cliente = null;
			
			cliente = this.buscarCredencial(nombreCredencial);
			
			libroEncontrado = this.buscarLibro(nombreLibro);
			libroEncontrado.devolver(cliente);
			
			return libroEncontrado.toString();
		} catch (CredencialException e) {
			throw e;
		} catch (Exception e) {
			throw new LibroException("No se pudo encontrar el libro, devido a: "+e.getMessage());
		}
	}
	
	/**
	 * Requiere ser Admin.
	 * @exception CredencialException
	 */
	public String subscribirCliente(String datosCliente) {
		
		if(!this.validarAdmin(this.usuarioActual.getNombre()))
			throw new CredencialException("Se necesita permiso de Admin para realizar esta operacion.");
		
		Credencial nueva = new Credencial(datosCliente);
		credenciales.add(nueva);
		return "Se subcribio " + nueva.toString();
	}
	
	private Boolean tieneLibrosAlquilados(String nombreCliente) {
		Boolean res = false;
		
		for(InfoLibro libro : this.libros) {
			if(libro.alquiladoPor(nombreCliente)) {
				res = true;
				break;
			}
		}
		
		return res;
	}
	
	/**
	 * 
	 * @param nombre
	 * @exception CredencialException
	 */
	public String desSubscribirCliente(String nombre) {
		try {
			if(!this.validarAdmin(this.usuarioActual.getNombre()))
				throw new CredencialException("Se necesita permiso de Admin para realizar esta operacion.");
			
			if(this.tieneLibrosAlquilados(nombre))
				throw new CredencialException("El cliente aun tiene libros alquilados.");
			
			Credencial cliente = new Credencial(nombre);
			if(!credenciales.remove(cliente))
				throw new CredencialException("No existe el cliente.");
			
			return "Se des-subcribio " + nombre;
		} catch (Exception e) {
			throw new CredencialException("No se pudo remover la credencial: "+ e.getMessage());
		}
	}
	
	/**
	 * @deprecated
	 * Valida si el cliente existe y esta habilitado.
	 * @param nombre
	 * @return
	 * @exception CredencialException
	 */
	public Boolean validarCredencial(String nombre) {
		try {
			Boolean res = false;
			Credencial encontrado = this.buscarCredencial(nombre);
				
			if(encontrado.estaHabilitado()) {
				res = true;
			}
			
			return res;
		} catch (Exception e) {
			throw new CredencialException("No se pudo encontrar la credencial: "+ e.getMessage());
		}
	}
	
	/**
	 * Valida si el usuario existe y es admin.
	 * @param nombre
	 * @return
	 * @exception CredencialException
	 */
	public Boolean validarAdmin(String nombre) {
		try {
			Boolean res = false;
			Credencial encontrado = this.buscarCredencial(nombre);
			
			if(encontrado.esAdmin()) {
				res = true;
			}
			
			return res;
		} catch (Exception e) {
			throw new CredencialException("No se pudo encontrar la credencial: "+ e.getMessage());
		}
	}
	
	/**
	 * 
	 * @param nombre
	 * @return
	 * @exception CredencialException
	 */
	public Boolean validarClientePoseeLibro(String nombreCliente, String nombreLibro) {
		try {
			Boolean res = false;
			Credencial encontrado = this.buscarCredencial(nombreCliente);
				
			if(encontrado.estaHabilitado()) {
				res = true;
			}
			
			return res;
		} catch (Exception e) {
			throw new CredencialException("No se pudo encontrar la credencial: "+ e.getMessage());
		}
	}
	
	/**
	 * 
	 * @param nombre
	 * @return
	 * @exception CredencialException
	 */
	private Credencial buscarCredencial(String nombre) {
		Credencial aBuscar = new Credencial(nombre);
		Credencial encontrado = null;
		if(credenciales.contains(aBuscar)) {
			encontrado = credenciales.get(credenciales.indexOf(aBuscar));
		} else {
			throw new CredencialException("No existe la credencial con nombre: "+nombre);
		}
		
		return encontrado;
	}
	
	/**
	 * 
	 * @param nombre
	 * @return
	 * @exception LibroException
	 */
	private InfoLibro buscarLibro(String nombre) {
		InfoLibro aBuscar = new InfoLibro(nombre, 0);
		InfoLibro encontrado = null;
		if(libros.contains(aBuscar)) {
			encontrado = libros.get(libros.indexOf(aBuscar));
		} else {
			throw new LibroException("No existe el libro.");
		}
		
		return encontrado;
	}
	
	public String listarLibros() {
		return "Libros: " + libros.toString();
	}
	
	
	/**
	 * Solo los admins pueden consultarlo
	 * @param nombreCredencial
	 * @return
	 * @exception CredencialException
	 */
	public String listarCredenciales() {
		if(this.validarAdmin(this.usuarioActual.getNombre())) {
			return "Credenciales: " + credenciales.toString();
		} else {
			throw new CredencialException("Acceso invalido.");
		}
	}
	
	/**
	 * 
	 * @param nombreCredencia
	 * @param datosAdmin
	 * @return
	 * @exception CredencialException
	 */
	public String agregarAdmin(String datosAdmin) {
		String res = "No se pudo agregar la nuevo admin. ";
		try {
			if(this.validarAdmin(this.usuarioActual.getNombre())) {
				Credencial nueva = new Credencial(datosAdmin, true);
				credenciales.add(nueva);
				res = "Se agrego el nuevo admin: "+datosAdmin;
			}
			
			return res;
		} catch (Exception e) {
			throw new CredencialException(res+e.getMessage());
		}
		
	}
	
	/**
	 * 
	 * @param nombreCredencia
	 * @param datosAdmin
	 * @return
	 * @exception CredencialException
	 */
	public String cambiarEstadoHabilitacionCliente(String datosCliente) {
		String res = "No se pudo cambiar el estado de habilitacion del cliente. ";
		try {
			if(this.validarAdmin(this.usuarioActual.getNombre())) {
				Credencial aCambiarEstado = this.buscarCredencial(datosCliente);
				if(aCambiarEstado.estaHabilitado()) {
					aCambiarEstado.deshabilitar();
					res = "Se deshabilito a: "+datosCliente;
				} else {
					aCambiarEstado.habilitar();
					res = "Se habilito a: "+datosCliente;
				}
			}
			
			return res;
		} catch (Exception e) {
			throw new CredencialException(res+e.getMessage());
		}
	}
	
	public String loggearUsuario(String nombre) {
		String res = "No se pudo loggear el cliente. ";
		try {
			this.usuarioActual = this.buscarCredencial(nombre);
			res = "El cliente " + this.usuarioActual + " se pudo loggear correctamente.";
			
			return res;
		} catch (Exception e) {
			throw new CredencialException(res + e.getMessage());
		}
	}

	public Credencial getUsuarioActual() {
		return usuarioActual;
	}
	
	public String getInfoUsuarioActual() {
		return usuarioActual.toString();
	}
	
	/**
	 * Por ahora es publica, genera ejemplos para la biblioteca.
	 */
	public void crearBibliotecaEjemplo() {
		try {
			Credencial admin = new Credencial("Admin", true);
			Credencial unCliente = new Credencial("Ale");
			Credencial otroCliente = new Credencial("Maru");
			InfoLibro platon = new InfoLibro("La Republica", 1);
			InfoLibro unLibro = new InfoLibro("Dune", 1);
			InfoLibro otroLibro = new InfoLibro("Lo que el viento se llevo", 2);
			ArrayList<InfoLibro> libros = new ArrayList<>();
			libros.add(unLibro);
			libros.add(otroLibro);
			libros.add(platon);
			ArrayList<Credencial> credenciales = new ArrayList<>();
			credenciales.add(admin);
			credenciales.add(unCliente);
			credenciales.add(otroCliente);
			this.libros = libros;
			this.credenciales = credenciales;
		} catch (Exception e) {
			System.err.println(e.getMessage());
			throw e;
		}
	}
}
