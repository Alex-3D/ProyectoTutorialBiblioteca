package main;

import main.controller.ControlBiblioteca;
import main.model.biblioteca.Biblioteca;
import main.view.InterfazVista;
import main.view.TextualMenu;
import main.view.VisualMenu;

public class ProgramaBibliotecaMVC {

	public static void main(String[] args) {
		// el modelo:
		Biblioteca modelo = new Biblioteca();
		modelo.crearBibliotecaEjemplo();
		// la vista:
		//InterfazVista vista = new TextualMenu();
		InterfazVista vista = new VisualMenu();
		// y el control:
		ControlBiblioteca control = new ControlBiblioteca(vista, modelo);
		// configura la vista
		vista.setControlador(control);
		// y arranca la interfaz (vista):
		vista.run();
		}

	}