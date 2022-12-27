package biblioteca;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	private static Biblioteca biblioteca;
	private static String opciones = "Ingrse una de las siguientes opciones para continuar: \n "
			+ "a - agregar libro \n "
			+ "n - para subscribir nuevo cliente \n "
			+ "r - para retirar un libro \n "
			+ "d - Para desubscribir cliente \n "
			+ "e - Para Devolver libro \n "
			+ "l - Listar libros \n "
			+ "c - Listar Clientes \n "
			+ "b - Agregar libreria \n "
			+ "x - Agregar Admin \n "
			+ "h - Habilitar-Deshabilitar Cliente \n "
			+ "s - para salir \n ";

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		Boolean terminarPrograma = false;
		
		Libreria unaLibreria = new Libreria();
		Libreria otraLibreria = new Libreria();
		Credencial admin = new Credencial(1, "Admin", true);
		Credencial unCliente = new Credencial(2, "Ale");
		Credencial otroCliente = new Credencial(3, "Maru");
		Libro platon = new Libro("La Republica");
		Libro unLibro = new Libro("Dune");
		Libro otroLibro = new Libro("Lo que el viento se llevo");
		unaLibreria.agregarLibro(unLibro);
		unaLibreria.agregarLibro(otroLibro);
		otraLibreria.agregarLibro(platon);
		ArrayList<Libreria> librerias = new ArrayList<>();
		librerias.add(unaLibreria);
		librerias.add(otraLibreria);
		ArrayList<Credencial> credenciales = new ArrayList<>();
		credenciales.add(admin);
		credenciales.add(unCliente);
		credenciales.add(otroCliente);
		biblioteca = new Biblioteca(librerias, credenciales);
		
		while(!terminarPrograma) {
			try {
				System.out.println(opciones);
				String respuesta = input.nextLine();
				String respuestaSistema = "";
				switch (respuesta) {
				case "a":
					respuestaSistema = agregarLibro();
					break;
				case "n":
					respuestaSistema = agregarCliente();
					break;
				case "r":
					respuestaSistema = alquilarLibro();
					break;
				case "d":
					respuestaSistema = quitarCliente();
					break;
				case "e":
					respuestaSistema = devolverLibro();
					break;
				case "l":
					respuestaSistema = listarLibros();
					break;
				case "c":
					respuestaSistema = listarCredenciales();
					break;
				case "b":
					respuestaSistema = agregarLibreria();
					break;
				case "x":
					respuestaSistema = agregarAdmin();
					break;
				case "h":
					respuestaSistema = habilitarDeshabilitarCliente();
					break;
				case "s":
					terminarPrograma = true;
					respuestaSistema = "Programa terminado.";
					break;

				default:
					respuestaSistema = "Ingreso incorrecto";
					break;
				}
				
				System.out.println(respuestaSistema);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}

	}
	
	private static String alquilarLibro() {
		Scanner input = new Scanner(System.in);
		try {
			System.out.println("Ingrese nombre del cliente");
			String nombreCredencial = input.nextLine();
			String res = "";
			System.out.println("Ingrese nombre del libro");
			String nombreLibro = input.nextLine();
			Libro libro = biblioteca.alquilarLibro(nombreLibro, nombreCredencial);
			res = libro.toString();
			
			return res;
		} catch (Exception e) {
			throw e;
		}
		
	}
	
	private static String agregarLibro() {
		Scanner input = new Scanner(System.in);
		try {
			System.out.println("Ingrese nombre del libro");
			String nombre = input.nextLine();
			Libro nuevo = new Libro(nombre);
			biblioteca.agregarLibro(nuevo);
			String res = "Se agrego el libro" + nuevo.toString();
			
			return res;
		} catch (Exception e) {
			throw e;
		}
		
	}
	
	private static String agregarCliente() {
		Scanner input = new Scanner(System.in);
		try {
			System.out.println("Ingrese nombre del cliente");
			String nombreCliente = input.nextLine();
			biblioteca.subscribirCliente(nombreCliente);
			String res = "Se subcribio " + nombreCliente;
			
			return res;
		} catch (Exception e) {
			throw e;
		}
		
	}
	
	private static String quitarCliente() {
		Scanner input = new Scanner(System.in);
		try {
			System.out.println("Ingrese nombre del cliente");
			String nombreClienteADesSubscribir = input.nextLine();
			biblioteca.desSubscribirCliente(nombreClienteADesSubscribir);
			String res = "Se des-subcribio " + nombreClienteADesSubscribir;
			
			return res;
		} catch (Exception e) {
			throw e;
		}
		
	}
	
	private static String listarLibros() {
		try {
			String res = "Libros: ";
			res += biblioteca.listarLibros();
			
			return res;
		} catch (Exception e) {
			throw e;
		}
	}
	
	private static String listarCredenciales() {
		Scanner input = new Scanner(System.in);
		try {
			System.out.println("Ingrese nombre del cliente");
			String nombreCredencial = input.nextLine();
			String res = "Credenciales: ";
			res += biblioteca.listarCredenciales(nombreCredencial);
			
			return res;
		} catch (Exception e) {
			throw e;
		}
	}
	
	private static String devolverLibro() {
		Scanner input = new Scanner(System.in);
		try {
			System.out.println("Ingrese nombre del Cliente");
			String nombreCredencial= input.nextLine();
			System.out.println("Ingrese nombre del libro");
			String nombreLibro  = input.nextLine();
			String res = biblioteca.devolverLibro(nombreLibro, nombreCredencial);
			
			return res;
		} catch (Exception e) {
			throw e;
		}
		
	}
	
	private static String agregarLibreria() {
		Scanner input = new Scanner(System.in);
		try {
			System.out.println("Ingrese nombre del Credencial");
			String nombreCredencial= input.nextLine();
			String res = biblioteca.agregarLibreria(nombreCredencial);
			
			return res;
		} catch (Exception e) {
			throw e;
		}
	}
	
	private static String agregarAdmin() {
		Scanner input = new Scanner(System.in);
		try {
			System.out.println("Ingrese su nombre");
			String nombreCredencial= input.nextLine();
			System.out.println("Ingrese nombre del Nuevo Admin");
			String datosAdmin= input.nextLine();
			String res = biblioteca.agregarAdmin(nombreCredencial, datosAdmin);
			
			return res;
		} catch (Exception e) {
			throw e;
		}
	}
	
	private static String habilitarDeshabilitarCliente() {
		Scanner input = new Scanner(System.in);
		try {
			System.out.println("Ingrese su nombre");
			String nombreCredencial= input.nextLine();
			System.out.println("Ingrese nombre del cliente");
			String datosAdmin= input.nextLine();
			String res = biblioteca.cambiarEstadoHabilitacionCliente(nombreCredencial, datosAdmin);
			
			return res;
		} catch (Exception e) {
			throw e;
		}
	}

}
