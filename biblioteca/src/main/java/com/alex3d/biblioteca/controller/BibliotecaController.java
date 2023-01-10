package com.alex3d.biblioteca.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alex3d.biblioteca.model.commons.RequestDto;
import com.alex3d.biblioteca.model.commons.logginDto;
import com.alex3d.biblioteca.services.BibliotecaService;

@RestController
@RequestMapping("")
public class BibliotecaController {
	private final Logger log = LoggerFactory.getLogger(BibliotecaController.class);
	
	@Autowired
	private BibliotecaService bibliotecaServise;
	
	@PostMapping("loggin")
	public ResponseEntity<String> loggin(@RequestBody logginDto dto) {
		try {
			log.info("Agregando nuevo libro: {}", dto);
			String rta = bibliotecaServise.loggearUsuario(dto.getNombreCredencial());
			log.info(rta);

			return ResponseEntity.status(200).body(rta);
		} catch (Exception e) {
			log.info("Error while trying to loggin: {}, with message: "+e.getMessage(), dto);
			return ResponseEntity.status(500).body(e.getMessage());
		}
	}
	
	@PostMapping("agregar/libro")
	public ResponseEntity<String> agregarLibro(@RequestBody RequestDto dto) {
		try {
			log.info("Agregando nuevo libro: {}", dto);
			String rta = bibliotecaServise.agregarLibro(dto.getNombreLibro(), dto.getNumeroLibreria());
			log.info(rta);

			return ResponseEntity.status(200).body(rta);
		} catch (Exception e) {
			log.info("Error while trying to add new book: {}, with message: "+e.getMessage(), dto);
			return ResponseEntity.status(500).body(e.getMessage());
		}
	}
	
	@PostMapping("alquilar/libro")
	public ResponseEntity<String> alquilarLibro(@RequestBody RequestDto dto) {
		try {
			log.info("Agregando nuevo libro: {}", dto);
			String rta = bibliotecaServise.alquilarLibro(dto.getNombreLibro(), dto.getNombreCredencial());
			log.info(rta);

			return ResponseEntity.status(200).body(rta);
		} catch (Exception e) {
			log.info("Error while trying to add new book: {}, with message: "+e.getMessage(), dto);
			return ResponseEntity.status(500).body(e.getMessage());
		}
	}
	
	@PostMapping("devolver/libro")
	public ResponseEntity<String> devolverLibro(@RequestBody RequestDto dto) {
		try {
			log.info("Devolviendo libro: {}", dto);
			String rta = bibliotecaServise.devolverLibro(dto.getNombreLibro(), dto.getNombreCredencial());
			log.info(rta);

			return ResponseEntity.status(200).body(rta);
		} catch (Exception e) {
			log.info("Error while trying to add new book: {}, with message: "+e.getMessage(), dto);
			return ResponseEntity.status(500).body(e.getMessage());
		}
	}
	
	@GetMapping("listar/libros")
	public ResponseEntity<String> listarLibros() {
		try {
			log.info("Intentando listar libros.");
			String rta = bibliotecaServise.listarLibros();
			log.info(rta);

			return ResponseEntity.status(200).body(rta);
		} catch (Exception e) {
			log.info("Error while trying to get list with message: "+e.getMessage());
			return ResponseEntity.status(500).body(e.getMessage());
		}
	}
	
	@PostMapping("subscribir/credencial")
	public ResponseEntity<String> subscribirCredencial(@RequestBody RequestDto dto) {
		try {
			log.info("Subscribiendo nueva credencial: {}", dto);
			String rta = bibliotecaServise.subscribirCredencial(dto.getNombreCredencial());
			log.info(rta);

			return ResponseEntity.status(200).body(rta);
		} catch (Exception e) {
			log.info("Error while trying to add new credential: {}, with message: "+e.getMessage(), dto);
			return ResponseEntity.status(500).body(e.getMessage());
		}
	}
	
	@PostMapping("dessubscribir/credencial")
	public ResponseEntity<String> desSubscribirCredencial(@RequestBody RequestDto dto) {
		try {
			log.info("Des-subscribiendo credencial: {}", dto);
			String rta = bibliotecaServise.desSubscribirCredencial(dto.getNombreCredencial());
			log.info(rta);

			return ResponseEntity.status(200).body(rta);
		} catch (Exception e) {
			log.info("Error while trying to remove credential: {}, with message: "+e.getMessage(), dto);
			return ResponseEntity.status(500).body(e.getMessage());
		}
	}
	
	@PostMapping("habilitardeshablitar/credencial")
	public ResponseEntity<String> habilitarCredencial(@RequestBody RequestDto dto) {
		try {
			log.info("Buscando estado habilitacion credencial: {}", dto);
			String rta = bibliotecaServise.cambiarEstadoHabilitacionCredencial(dto.getNombreCredencial());
			log.info(rta);

			return ResponseEntity.status(200).body(rta);
		} catch (Exception e) {
			log.info("Error while trying to remove credential: {}, with message: "+e.getMessage(), dto);
			return ResponseEntity.status(500).body(e.getMessage());
		}
	}
	
	@GetMapping("listar/credenciales")
	public ResponseEntity<String> listarCredenciales() {
		try {
			log.info("Intentando listar credenciales.");
			String rta = bibliotecaServise.listarCredenciales();
			log.info(rta);

			return ResponseEntity.status(200).body(rta);
		} catch (Exception e) {
			log.info("Error while trying to get list with message: "+e.getMessage());
			return ResponseEntity.status(500).body(e.getMessage());
		}
	}
	
	@PostMapping("agregar/admin")
	public ResponseEntity<String> agregarAdmin(@RequestBody RequestDto dto) {
		try {
			log.info("Agregar nuevo admin: {}", dto);
			String rta = bibliotecaServise.agregarAdmin(dto.getNombreCredencial());
			log.info(rta);

			return ResponseEntity.status(200).body(rta);
		} catch (Exception e) {
			log.info("Error while trying to add new admin: {}, with message: "+e.getMessage(), dto);
			return ResponseEntity.status(500).body(e.getMessage());
		}
	}
	
	@GetMapping("salir")
	public ResponseEntity<String> salir() {
		try {
			log.info("Terminando el programa");
			bibliotecaServise.salir();

			return ResponseEntity.status(200).body("Programa Terminado.");
		} catch (Exception e) {
			log.info("Error while trying to exit with message: "+e.getMessage());
			return ResponseEntity.status(500).body(e.getMessage());
		}
	}
	
}
