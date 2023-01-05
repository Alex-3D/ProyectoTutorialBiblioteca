package main.view;

import main.commons.RequestDto;
import main.commons.enums.Opcion;
import main.controller.ControlBiblioteca;

public interface InterfazVista {
	void setControlador(ControlBiblioteca c);
	void setIsLogged(Boolean isLogged);
	void run(); // comienza la visualización
	
	Opcion getOpcionIngresadaPorUsuario();
	RequestDto getRequestInfo();
	void escribirRespuestaDelSistema(String respuesta);
	/**
	 * Imprime mensaje de salida.
	 * @param respuesta
	 */
	void salir(String respuesta);
}
