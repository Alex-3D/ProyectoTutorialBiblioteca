package biblioteca;

import java.util.ArrayList;
import java.util.Scanner;

public class MenuBiblioteca {
	private static Biblioteca biblioteca;
	private static String opciones = "Ingrse una de las siguientes opciones para continuar: \n "
			+ "a - agregar libro \n "
			+ "n - para subscribir nuevo cliente \n "
			+ "r - para retirar un libro \n "
			+ "d - Para desubscribir cliente \n "
			+ "e - Para Devolver libro \n "
			+ "l - Listar libros \n "
			+ "c - Listar Clientes \n "
			+ "x - Agregar Admin \n "
			+ "h - Habilitar-Deshabilitar Cliente \n "
			+ "s - para salir \n ";

	public static void run() {
		crearBibliotecaEjemplo();
		runMenu();
	}
	
	private static void runMenu() {
		Scanner input = new Scanner(System.in);
		
		Boolean terminarPrograma = false;
		
		if(biblioteca == null) {
			crearBibliotecaVacia();
		}
		
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
	
	private static void crearBibliotecaVacia() {
		biblioteca = new Biblioteca();
	}
	
	private static void crearBibliotecaEjemplo() {
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
			biblioteca = new Biblioteca(libros, credenciales);
		} catch (Exception e) {
			System.err.println(e.getMessage());
			throw e;
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
			InfoLibro libro = biblioteca.alquilarLibro(nombreLibro, nombreCredencial);
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
			System.out.println("Ingrese numero libreria");
			Integer numeroLibreria = input.nextInt();
			InfoLibro nuevo = biblioteca.agregarLibro(nombre, numeroLibreria);
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
			Credencial nueva = biblioteca.subscribirCliente(nombreCliente);
			String res = "Se subcribio " + nueva.toString();
			
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
			String res = biblioteca.desSubscribirCliente(nombreClienteADesSubscribir);
			
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
			System.out.println("Ingrese nu nombre");
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
			InfoLibro devuelto = biblioteca.devolverLibro(nombreLibro, nombreCredencial);
			String res = devuelto.toString();
			
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
