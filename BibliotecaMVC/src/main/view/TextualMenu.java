package main.view;

import java.util.Scanner;

import main.commons.RequestDto;
import main.commons.enums.Opcion;
import main.controller.ControlBiblioteca;

public class TextualMenu implements InterfazVista {
	private ControlBiblioteca controlador;
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
	
	private Opcion opcionElegida;
	private String respuestaSistema;
	private Boolean isLogged = false;
	private RequestDto requestInfo;

	@Override
	public void setControlador(ControlBiblioteca c) {
		//TODO: Ver si es necesario
		this.controlador = c;
	}

	@Override
	public void run() {
		if(this.isLogged) {
			runMenu();
		} else {
			loggin();
		}
	}

	@Override
	public Opcion getOpcionIngresadaPorUsuario() {
		return this.opcionElegida;
	}

	@Override
	public void escribirRespuestaDelSistema(String respuesta) {
		System.out.println(respuesta);
		if(this.isLogged) {
			runMenu();
		} else {
			loggin();
		}
	}
	
	@Override
	public void salir(String respuesta) {
		System.out.println(respuesta);
	}
	
	private void runMenu() {
		Scanner input = new Scanner(System.in);
		
		Boolean terminarPrograma = false;
		
		try {
			System.out.println(opciones);
			String respuesta = input.nextLine();
			this.respuestaSistema = "";
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
				this.salir();
				break;

			default:
				respuestaSistema = "Ingreso incorrecto";
				this.escribirRespuestaDelSistema(respuestaSistema);
				break;
			}
			
			System.out.println(respuestaSistema);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	private String alquilarLibro() {
		Scanner input = new Scanner(System.in);
		try {
			System.out.println("Ingrese nombre del cliente");
			String nombreCredencial = input.nextLine();
			String res = "";
			System.out.println("Ingrese nombre del libro");
			String nombreLibro = input.nextLine();
			RequestDto infoDto = new RequestDto();
			infoDto.setNombreLibro(nombreLibro);
			infoDto.setNombreCredencial(nombreCredencial);
			this.requestInfo = infoDto;
			this.opcionElegida = Opcion.retirarLibro;
			
			this.controlador.procesarEleccion();
			res = "Intentando retirar libro.";
			
			return res;
		} catch (Exception e) {
			throw e;
		}
		
	}
	
	private String agregarLibro() {
		Scanner input = new Scanner(System.in);
		try {
			System.out.println("Ingrese nombre del libro");
			String nombre = input.nextLine();
			System.out.println("Ingrese numero libreria");
			Integer numeroLibreria = input.nextInt();
			String res = "Intentando agregar nuevo libro.";
			RequestDto infoDto = new RequestDto();
			infoDto.setNombreLibro(nombre);
			infoDto.setNumeroLibreria(numeroLibreria);
			this.requestInfo = infoDto;
			this.opcionElegida = Opcion.agregarLibro;
			
			this.controlador.procesarEleccion();
			//this.controlador.actionPerformed(new ActionEvent(this, 0, this.opcionElegida.toString()));
			
			return res;
		} catch (Exception e) {
			throw e;
		}
		
	}
	
	private String agregarCliente() {
		Scanner input = new Scanner(System.in);
		try {
			System.out.println("Ingrese nombre del cliente");
			String nombreCliente = input.nextLine();
			RequestDto infoDto = new RequestDto();
			infoDto.setNombreCredencial(nombreCliente);
			this.requestInfo = infoDto;
			this.opcionElegida = Opcion.subscribirCliente;
			
			this.controlador.procesarEleccion();
			String res = "Intentando subscribir cliente";
			
			return res;
		} catch (Exception e) {
			throw e;
		}
		
	}
	
	private String quitarCliente() {
		Scanner input = new Scanner(System.in);
		try {
			System.out.println("Ingrese nombre del cliente");
			String nombreClienteADesSubscribir = input.nextLine();
			RequestDto infoDto = new RequestDto();
			infoDto.setNombreCredencial(nombreClienteADesSubscribir);
			this.requestInfo = infoDto;
			this.opcionElegida = Opcion.desSubscribirCliente;
			
			this.controlador.procesarEleccion();
			String res = "Intentando des-subscribir cliente";
			
			return res;
		} catch (Exception e) {
			throw e;
		}
		
	}
	
	private String listarLibros() {
		try {
			String res = "Intentando listar libros. ";
			RequestDto infoDto = new RequestDto();
			this.requestInfo = infoDto;
			this.opcionElegida = Opcion.listarLibros;
			
			this.controlador.procesarEleccion();
			
			return res;
		} catch (Exception e) {
			throw e;
		}
	}
	
	private String listarCredenciales() {
		Scanner input = new Scanner(System.in);
		try {
			String res = "Intentando listar credenciales.";
			RequestDto infoDto = new RequestDto();
			this.requestInfo = infoDto;
			this.opcionElegida = Opcion.listarCredenciales;
			
			this.controlador.procesarEleccion();
			
			return res;
		} catch (Exception e) {
			throw e;
		}
	}
	
	private String devolverLibro() {
		Scanner input = new Scanner(System.in);
		try {
			System.out.println("Ingrese nombre del Cliente");
			String nombreCredencial= input.nextLine();
			System.out.println("Ingrese nombre del libro");
			String nombreLibro  = input.nextLine();
			RequestDto infoDto = new RequestDto();
			infoDto.setNombreCredencial(nombreCredencial);
			infoDto.setNombreLibro(nombreLibro);
			this.requestInfo = infoDto;
			this.opcionElegida = Opcion.devolverLibro;
			
			this.controlador.procesarEleccion();
			String res = "Intentando devolver libro.";
			
			return res;
		} catch (Exception e) {
			throw e;
		}
		
	}
	
	private String agregarAdmin() {
		Scanner input = new Scanner(System.in);
		try {
			System.out.println("Ingrese nombre del Nuevo Admin");
			String datosAdmin= input.nextLine();
			String res = "Intentando agregar nuevo admin.";
			RequestDto infoDto = new RequestDto();
			infoDto.setNombreCredencial(datosAdmin);
			this.requestInfo = infoDto;
			this.opcionElegida = Opcion.agregarAdmin;
			
			this.controlador.procesarEleccion();
			
			return res;
		} catch (Exception e) {
			throw e;
		}
	}
	
	private String habilitarDeshabilitarCliente() {
		Scanner input = new Scanner(System.in);
		try {
			System.out.println("Ingrese nombre del cliente");
			String nombreCredencial= input.nextLine();
			String res = "Intentando cambiar estado habilitacion.";
			RequestDto infoDto = new RequestDto();
			infoDto.setNombreCredencial(nombreCredencial);
			this.requestInfo = infoDto;
			this.opcionElegida = Opcion.habilitarDeshabilitarCredencial;
			
			this.controlador.procesarEleccion();
			
			return res;
		} catch (Exception e) {
			throw e;
		}
	}
	
	private void salir() {
		try {
			RequestDto infoDto = new RequestDto();
			this.requestInfo = infoDto;
			this.opcionElegida = Opcion.salir;
			
			this.controlador.procesarEleccion();
			
		} catch (Exception e) {
			throw e;
		}
	}
	
	
	
	public Boolean getIsLogged() {
		return isLogged;
	}

	@Override
	public void setIsLogged(Boolean isLogged) {
		this.isLogged = isLogged;
	}

	private void loggin() {
		try {
			Scanner input = new Scanner(System.in);
			System.out.println("Ingrese su nombre");
			String nombreCredencial= input.nextLine();
			controlador.loggin(nombreCredencial);
		} catch (Exception e) {
			this.escribirRespuestaDelSistema("No se pudo completar el loggin, porque: "+e.getMessage());
		}
	}

	@Override
	public RequestDto getRequestInfo() {
		if (this.requestInfo == null)
			throw new RuntimeException("No ah sido escogido respuesta aun.");
		
		return this.requestInfo;
	}

}
