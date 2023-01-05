package main.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.commons.RequestDto;
import main.commons.enums.Opcion;
import main.model.biblioteca.Biblioteca;
import main.view.InterfazVista;

public class ControlBiblioteca implements ActionListener {
	private InterfazVista vista;
	private Biblioteca modelo;

	public ControlBiblioteca(InterfazVista vista, Biblioteca modelo) {
		this.vista = vista;
		this.modelo = modelo;
	}

	@Override
	public void actionPerformed(ActionEvent evento) {
		Opcion opcionElegida = vista.getOpcionIngresadaPorUsuario();
		
	}
	
	/**
	 * Procesa la Opcion elegida por el usuario.
	 * @param info
	 * @exception RuntimeException, CredencialException, LibroException
	 */
	public void procesarEleccion() {
		Opcion opcionElegida = vista.getOpcionIngresadaPorUsuario();
		RequestDto info = vista.getRequestInfo();
		Boolean terminarPrograma = false;
		
		try {
			String respuestaSistema = "";
			switch (opcionElegida) {
			case agregarLibro:
				respuestaSistema = modelo.agregarLibro(info.getNombreLibro(), info.getNumeroLibreria());
				break;
			case subscribirCliente:
				respuestaSistema = modelo.subscribirCliente(info.getNombreCredencial());
				break;
			case retirarLibro:
				respuestaSistema = modelo.alquilarLibro(info.getNombreLibro(),info.getNombreCredencial());
				break;
			case desSubscribirCliente:
				respuestaSistema = modelo.desSubscribirCliente(info.getNombreCredencial());
				break;
			case devolverLibro:
				respuestaSistema = modelo.devolverLibro(info.getNombreLibro(),info.getNombreCredencial());
				break;
			case listarLibros:
				respuestaSistema = modelo.listarLibros();
				break;
			case listarCredenciales:
				respuestaSistema = modelo.listarCredenciales();
				break;
			case agregarAdmin: //TODO: revisar esto
				respuestaSistema = modelo.agregarAdmin(info.getNombreCredencial());
				break;
			case habilitarDeshabilitarCredencial: //TODO: revisar esto
				respuestaSistema = modelo.cambiarEstadoHabilitacionCliente(info.getNombreCredencial());
				break;
			case salir:
				terminarPrograma = true;
				respuestaSistema = "Programa terminado.";
				this.vista.salir(respuestaSistema);
				break;

			default:
				respuestaSistema = "Ingreso incorrecto";
				break;
			}
			
			if(!terminarPrograma) {
				this.vista.escribirRespuestaDelSistema(respuestaSistema);
			} else {
				System.exit(0);
			}
			
		} catch (Exception e) {
			this.vista.escribirRespuestaDelSistema(e.getMessage());
		}
	}
	
	/**
	 * Loggeamos el usuario, por ahora sin pass.
	 * @exception
	 */
	public void loggin(String nombre) {
		try {
			String res = this.modelo.loggearUsuario(nombre);
			this.vista.setIsLogged(true);
			this.vista.escribirRespuestaDelSistema(res);
		} catch (Exception e) {
			this.vista.escribirRespuestaDelSistema(e.getMessage());
		}
	}
	
}
