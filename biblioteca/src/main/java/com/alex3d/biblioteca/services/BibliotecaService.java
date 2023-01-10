package com.alex3d.biblioteca.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alex3d.biblioteca.model.Biblioteca;

@Service
public class BibliotecaService {
	private static final Logger log = LoggerFactory.getLogger(BibliotecaService.class);
	
	private Biblioteca bibliotecaModel;
	
	public BibliotecaService() {
		this.bibliotecaModel = new Biblioteca();
		this.bibliotecaModel.crearBibliotecaEjemplo();
	}
	
	public String loggearUsuario(String nombreUsuario) {
		log.info("Intentando loggear usuario: "+ nombreUsuario);
		return this.bibliotecaModel.loggearUsuario(nombreUsuario);
	}

	public String agregarLibro(String nombreLibro, Integer numeroLibreria) {
		log.info("Intentando agregar libro: "+ nombreLibro + " en la libreria: "+ numeroLibreria);
		return this.bibliotecaModel.agregarLibro(nombreLibro, numeroLibreria);
	}
	
	public String alquilarLibro(String nombreLibro, String nombreCredencial) {
		log.info("Intentando alquilar libro: "+ nombreLibro + " por la credencial: "+ nombreCredencial);
		return this.bibliotecaModel.alquilarLibro(nombreLibro, nombreCredencial);
	}
	
	public String devolverLibro(String nombreLibro, String nombreCredencial) {
		log.info("Intentando devolver libro: "+ nombreLibro + " por la credencial: "+ nombreCredencial);
		return this.bibliotecaModel.devolverLibro(nombreLibro, nombreCredencial);
	}
	
	public String listarLibros() {
		log.info("listando: ");
		return this.bibliotecaModel.listarLibros();
	}
	
	public String subscribirCredencial(String nombreCredencial) {
		log.info("Intentando subscribir credencial con nombre: "+ nombreCredencial);
		return this.bibliotecaModel.subscribirCliente(nombreCredencial);
	}
	
	public String desSubscribirCredencial(String nombreCredencial) {
		log.info("Intentando des-subscribir credencial con nombre: "+ nombreCredencial);
		return this.bibliotecaModel.desSubscribirCliente(nombreCredencial);
	}
	
	public String cambiarEstadoHabilitacionCredencial(String nombreCredencial) {
		log.info("Intentando cambiar estado habilitacion de credencial con nombre: "+ nombreCredencial);
		return this.bibliotecaModel.cambiarEstadoHabilitacionCliente(nombreCredencial);
	}
	
	public String listarCredenciales() {
		log.info("listando: ");
		return this.bibliotecaModel.listarCredenciales();
	}
	
	public String agregarAdmin(String nombreCredencial) {
		log.info("Intentando agregar credencial: "+ nombreCredencial);
		return this.bibliotecaModel.agregarAdmin(nombreCredencial);
	}
	
	public void salir() {
		log.info("Programa Terminando.");
		System.exit(0);
	}
	
	

}
