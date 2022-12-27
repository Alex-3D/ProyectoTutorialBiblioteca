package biblioteca;

import java.util.ArrayList;
import java.util.List;

import biblioteca.excepciones.CredencialException;
import biblioteca.excepciones.LibroException;

public class Biblioteca {
	private List<Libreria> librerias;
	private List<Credencial> credenciales;
	private Integer idUltimoCliente=4;
	
	
	
	public Biblioteca() {
		this.librerias = new ArrayList<>();
		this.credenciales = new ArrayList<>();
	}
	
	public Biblioteca(List<Libreria> librerias) {
		this.librerias = librerias;
		this.credenciales = new ArrayList<>();
	}

	public Biblioteca(List<Libreria> librerias, List<Credencial> credenciales) {
		this.librerias = librerias;
		this.credenciales = credenciales;
	}

	/**
	 * 
	 * @param nuevo
	 * @exception LibroException
	 */
	public void agregarLibro(Libro nuevo) {
		try {
			Boolean seAgrego = false;
			int indice = 0;
			
			while(!seAgrego && indice < librerias.size()) {
				Libreria unaLibreria = librerias.get(indice);
				seAgrego = unaLibreria.agregarLibro(nuevo);
				indice++;
			}
			
			if(!seAgrego)
				throw new LibroException("No se pudo agregar el Libro nuevo: "+ nuevo.toString());
		} catch (LibroException e) {
			throw e;
		} catch (Exception e) {
			throw new LibroException("No se pudo agregar el libro: "+nuevo.toString()+" devido a: "+ e.getMessage());
		}
	}
	
	//TODO: El proceso de busqueda de libro, deberia estar aparte y no acoplado entre los metodos.
	
	/**
	 * 
	 * @param nombreLibro
	 * @param nombreCredencial
	 * @return
	 * @exception CredencialException, LibroException
	 */
	public Libro alquilarLibro(String nombreLibro, String nombreCredencial) {
		try {
			Libro libroEncontrado = null;
			
			if(this.validarCredencial(nombreCredencial)) {
				for(Libreria libreria : librerias) {
					if(libreria.contieneLibro(nombreLibro)) {
						libroEncontrado = libreria.alquilarLibro(nombreLibro, nombreCredencial);
						break;
					}
				}
			} else {
				throw new CredencialException("No se encontro la credencial");
			}
			
			if (libroEncontrado == null)
				throw new LibroException("No existe el Libro.");
			
			return libroEncontrado;
		} catch (CredencialException e) {
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
			String res = "";
			if(this.validarCredencial(nombreCredencial)) {
				for(Libreria libreria : librerias) {
					if(libreria.contieneLibro(nombreLibro)) {
						res = libreria.devolverLibro(nombreLibro, nombreCredencial);
						break;
					}
				}
			} else {
				throw new CredencialException("No se encontro la credencial");
			}
			
			if(res.isEmpty())
				throw new LibroException("No existe el libro.");
			
			return res;
		} catch (CredencialException e) {
			throw e;
		} catch (Exception e) {
			throw new LibroException("No se pudo encontrar el libro, devido a: "+e.getMessage());
		}
	}
	
	public void subscribirCliente(String datosCliente) {
		Credencial nueva = new Credencial(this.idUltimoCliente, datosCliente);
		credenciales.add(nueva);
		this.idUltimoCliente++;
	}
	
	/**
	 * 
	 * @param nombre
	 * @exception CredencialException
	 */
	public void desSubscribirCliente(String nombre) {
		try {
			Credencial cliente = new Credencial(0, nombre);
			if(!credenciales.remove(cliente))
				throw new CredencialException("No existe el cliente");
		} catch (Exception e) {
			throw new CredencialException("No se pudo remover la credencial: "+ e.getMessage());
		}
	}
	
	/**
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
	 * Valida si el cliente existe y esta habilitado.
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
	
	private Credencial buscarCredencial(String nombre) {
		Credencial aBuscar = new Credencial(0, nombre);
		Credencial encontrado = null;
		if(credenciales.contains(aBuscar)) {
			encontrado = credenciales.get(credenciales.indexOf(aBuscar));
		} else {
			throw new CredencialException("No existe.");
		}
		
		return encontrado;
	}
	
	/**
	 * 
	 * @param nombreCredencial
	 * @return
	 * @exception CredencialException
	 */
	public String agregarLibreria(String nombreCredencial) {
		String res = "No se pudo agregar la libreria. ";
		try {
			if(this.validarAdmin(nombreCredencial)) {
				Libreria nueva = new Libreria();
				librerias.add(nueva);
				res = "Libreria agregada.";
			}
			
			return res;
		} catch (Exception e) {
			throw new CredencialException(res+e.getMessage());
		}
	}
	
	public String listarLibros() {
		return librerias.toString();
	}
	
	
	/**
	 * Solo los admins pueden consultarlo
	 * @param nombreCredencial
	 * @return
	 * @exception CredencialException
	 */
	public String listarCredenciales(String nombreCredencial) {
		if(this.validarAdmin(nombreCredencial)) {
			return credenciales.toString();
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
	public String agregarAdmin(String nombreCredencia, String datosAdmin) {
		String res = "No se pudo agregar la nuevo admin. ";
		try {
			if(this.validarAdmin(nombreCredencia)) {
				Credencial nueva = new Credencial(this.idUltimoCliente, datosAdmin, true);
				credenciales.add(nueva);
				this.idUltimoCliente++;
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
	public String cambiarEstadoHabilitacionCliente(String nombreCredencia, String datosCliente) {
		String res = "No se pudo cambiar el estado de habilitacion del cliente. ";
		try {
			if(this.validarAdmin(nombreCredencia)) {
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
}
