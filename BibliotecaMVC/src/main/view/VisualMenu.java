package main.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.HeadlessException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import main.commons.RequestDto;
import main.commons.enums.Opcion;
import main.controller.ControlBiblioteca;
import java.awt.event.ActionListener;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import javax.swing.SwingConstants;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import java.awt.Color;

public class VisualMenu extends JFrame implements InterfazVista, ActionListener {

	private Boolean isLogged = false;
	private Opcion opcionElegida;
	private RequestDto requestInfo;
	private String respuestaSistema;
	private ControlBiblioteca controlador;
	private JTextField cantidad;
	private JLabel resultado;
	private JButton agregarLibro;
    private JButton alquilarLibro;
    private JTextField nombreTextField;
	private JLabel nombreLabel;
	private JTextField libroTextField;
	private JTextField libreriaTextField;
	private JTextField logginTextField;
	private JButton btnEnviarRespuesta;
	private JTextPane respuestaTextPanel;
	private JButton salir;
	private JButton devolverLibro;
	private JButton listarLibros;
	private JButton subscribirCredencial;
	private JButton desSubscribirCredencial;
	private JButton listarCredenciales;
	private JButton habilitarCredencial;
	private JButton agregarAdmin;

	@Override
	public void setControlador(ControlBiblioteca c) {
		this.controlador = c;
	}

	@Override
	public void setIsLogged(Boolean isLogged) {
		this.isLogged = isLogged;
		// una vez loggeado hay que activar la botonera y desactivar el campo loggin.
		if(isLogged) {
			this.disabledTextFieldsAndBtnEnviarRespuesta();
			this.activarBotonera();
			
		}
	}

	@Override
	public void run() {
		pack();// coloca los componentes
		setLocationRelativeTo(null);// centra la ventana en la pantalla
		setVisible(true);// visualiza la ventana
		
	}

	@Override
	public Opcion getOpcionIngresadaPorUsuario() {
		return this.opcionElegida;
	}

	@Override
	public void escribirRespuestaDelSistema(String respuesta) {
		this.respuestaSistema = respuesta;
		this.respuestaTextPanel.setText(respuestaSistema);
	}
	
	@Override
	public RequestDto getRequestInfo() {
		if (this.requestInfo == null)
			throw new RuntimeException("No ah sido escogido respuesta aun.");
		
		return this.requestInfo;
	}

	@Override
	public void salir(String respuesta) {
		System.out.println(respuesta);
		this.escribirRespuestaDelSistema(respuestaSistema);
		
	}

	public VisualMenu() throws HeadlessException {
		super("Biblioteca");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setMinimumSize(new Dimension(1024, 1024));
		
		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setLayout(new BorderLayout(10,10));
		
		cantidad = new JTextField(8);
		JPanel panelImput = new JPanel(); 
		panelImput.getLayout();panelImput.add(cantidad);
		panelPrincipal.add(panelImput, BorderLayout.SOUTH);
		
		resultado = new JLabel("Ingrse nombre");
		resultado.setBounds(460, 5, 103, 15);
		JPanel panelRespuesta = new JPanel(); panelRespuesta.setLayout(null);
		panelRespuesta.add(resultado);
		panelPrincipal.add(panelRespuesta, BorderLayout.SOUTH);
		
		
		/*private static String opciones = "Ingrse una de las siguientes opciones para continuar: \n "
			+ "a - agregar libro \n "
			+ "n - para subscribir nuevo cliente \n "
			+ "r - para retirar un libro \n "
			+ "d - Para desubscribir cliente \n "
			+ "e - Para Devolver libro \n "
			+ "l - Listar libros \n "
			+ "c - Listar Clientes \n "
			+ "x - Agregar Admin \n "
			+ "h - Habilitar-Deshabilitar Cliente \n "
			+ "s - para salir \n ";*/
		agregarLibro = new JButton("Agregar Libro");
		agregarLibro.setEnabled(false);
		agregarLibro.setBounds(230, 79, 250, 25);
        agregarLibro.setActionCommand(Opcion.agregarLibro.toString());
        agregarLibro.addActionListener(this);
        alquilarLibro = new JButton("Alquilar Libro");
        alquilarLibro.setEnabled(false);
        alquilarLibro.setBounds(516, 79, 250, 25);
        alquilarLibro.setActionCommand(Opcion.retirarLibro.toString());
        alquilarLibro.addActionListener(this);
        JPanel botoneraPanel = new JPanel();
        botoneraPanel.setLayout(null);
        botoneraPanel.add(agregarLibro); botoneraPanel.add(alquilarLibro);
        panelPrincipal.add(botoneraPanel, BorderLayout.CENTER);
        
        JLabel lablelBotonera = new JLabel("Elija alguna opción.");
        lablelBotonera.setBounds(0, 22, 1024, 33);
        botoneraPanel.add(lablelBotonera);
        lablelBotonera.setHorizontalAlignment(SwingConstants.CENTER);
        
        respuestaTextPanel = new JTextPane();
        respuestaTextPanel.setText("Ingrese su nombre para loggear su usuario.");
        respuestaTextPanel.setEditable(false);
        respuestaTextPanel.setBounds(265, 558, 493, 119);
        botoneraPanel.add(respuestaTextPanel);
        
        nombreTextField = new JTextField();
        nombreTextField.setEnabled(false);
        nombreTextField.setBounds(51, 467, 270, 40);
        botoneraPanel.add(nombreTextField);
        nombreTextField.setColumns(10);
        
        nombreLabel = new JLabel("Ingrese Nombre Credencial");
        nombreLabel.setVerticalAlignment(SwingConstants.BOTTOM);
        nombreLabel.setHorizontalAlignment(SwingConstants.CENTER);
        nombreLabel.setBounds(65, 422, 214, 33);
        botoneraPanel.add(nombreLabel);
        
        btnEnviarRespuesta = new JButton("Enviar Respuesta");
        btnEnviarRespuesta.addActionListener(this);
        btnEnviarRespuesta.setBounds(454, 689, 157, 25);
        btnEnviarRespuesta.setActionCommand("respuestaUsuario");
        botoneraPanel.add(btnEnviarRespuesta);
        
        JLabel libroLabel = new JLabel("Ingrese Nombre Libro");
        libroLabel.setVerticalAlignment(SwingConstants.BOTTOM);
        libroLabel.setHorizontalAlignment(SwingConstants.CENTER);
        libroLabel.setBounds(373, 422, 214, 33);
        botoneraPanel.add(libroLabel);
        
        libroTextField = new JTextField();
        libroTextField.setEnabled(false);
        libroTextField.setColumns(10);
        libroTextField.setBounds(349, 467, 280, 40);
        botoneraPanel.add(libroTextField);
        
        JLabel libreriaLabel = new JLabel("Ingrese Numero Libreria");
        libreriaLabel.setVerticalAlignment(SwingConstants.BOTTOM);
        libreriaLabel.setHorizontalAlignment(SwingConstants.CENTER);
        libreriaLabel.setBounds(708, 422, 214, 33);
        botoneraPanel.add(libreriaLabel);
        
        libreriaTextField = new JTextField();
        libreriaTextField.setEnabled(false);
        libreriaTextField.setColumns(10);
        libreriaTextField.setBounds(687, 467, 277, 40);
        botoneraPanel.add(libreriaTextField);
        
        JLabel logginLabel = new JLabel("Ingrese Nombre Credencial para Loggin");
        logginLabel.setVerticalAlignment(SwingConstants.BOTTOM);
        logginLabel.setHorizontalAlignment(SwingConstants.CENTER);
        logginLabel.setBounds(349, 325, 295, 33);
        botoneraPanel.add(logginLabel);
        
        logginTextField = new JTextField();
        logginTextField.setColumns(10);
        logginTextField.setBounds(366, 370, 270, 40);
        botoneraPanel.add(logginTextField);
        
        salir = new JButton("Salir");
        salir.setEnabled(false);
        salir.setActionCommand(Opcion.salir.toString());
        salir.setBounds(438, 258, 131, 25);
        salir.addActionListener(this);
        botoneraPanel.add(salir);
        
        devolverLibro = new JButton("Devolver Libro");
        devolverLibro.setEnabled(false);
        devolverLibro.setActionCommand(Opcion.devolverLibro.toString());
        devolverLibro.setBounds(230, 111, 250, 25);
        devolverLibro.addActionListener(this);
        botoneraPanel.add(devolverLibro);
        
        listarLibros = new JButton("Listar Libros");
        listarLibros.setEnabled(false);
        listarLibros.setActionCommand(Opcion.listarLibros.toString());
        listarLibros.setBounds(516, 111, 250, 25);
        listarLibros.addActionListener(this);
        botoneraPanel.add(listarLibros);
        
        subscribirCredencial = new JButton("Subscribir Credencial");
        subscribirCredencial.setEnabled(false);
        subscribirCredencial.setActionCommand(Opcion.subscribirCliente.toString());
        subscribirCredencial.setBounds(230, 142, 250, 25);
        subscribirCredencial.addActionListener(this);
        botoneraPanel.add(subscribirCredencial);
        
        desSubscribirCredencial = new JButton("Des-subscribir Credencial");
        desSubscribirCredencial.setEnabled(false);
        desSubscribirCredencial.setActionCommand(Opcion.desSubscribirCliente.toString());
        desSubscribirCredencial.setBounds(516, 142, 250, 25);
        desSubscribirCredencial.addActionListener(this);
        botoneraPanel.add(desSubscribirCredencial);
        
        listarCredenciales = new JButton("Listar Credenciales");
        listarCredenciales.setEnabled(false);
        listarCredenciales.setActionCommand(Opcion.listarCredenciales.toString());
        listarCredenciales.setBounds(230, 174, 250, 25);
        listarCredenciales.addActionListener(this);
        botoneraPanel.add(listarCredenciales);
        
        habilitarCredencial = new JButton("Habilitar des-habilitar Credencial");
        habilitarCredencial.setEnabled(false);
        habilitarCredencial.setActionCommand(Opcion.habilitarDeshabilitarCredencial.toString());
        habilitarCredencial.setBounds(516, 174, 250, 25);
        habilitarCredencial.addActionListener(this);
        botoneraPanel.add(habilitarCredencial);
        
        agregarAdmin = new JButton("Agregar Admin");
        agregarAdmin.setEnabled(false);
        agregarAdmin.setActionCommand(Opcion.agregarAdmin.toString());
        agregarAdmin.setBounds(230, 211, 250, 25);
        agregarAdmin.addActionListener(this);
        botoneraPanel.add(agregarAdmin);
        getContentPane().add(panelPrincipal);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			this.respuestaSistema = "";
			switch (e.getActionCommand()) {
			case "agregarLibro":
				respuestaSistema = agregarLibro();
				break;
			case "subscribirCliente":
				respuestaSistema = agregarCliente();
				break;
			case "retirarLibro":
				respuestaSistema = alquilarLibro();
				break;
			case "desSubscribirCliente":
				respuestaSistema = quitarCliente();
				break;
			case "devolverLibro":
				respuestaSistema = devolverLibro();
				break;
			case "listarLibros":
				respuestaSistema = listarLibros();
				break;
			case "listarCredenciales":
				respuestaSistema = listarCredenciales();
				break;
			case "agregarAdmin":
				respuestaSistema = agregarAdmin();
				break;
			case "habilitarDeshabilitarCredencial":
				respuestaSistema = habilitarDeshabilitarCliente();
				break;
			case "salir":
				this.salir();
				break;
			case "respuestaUsuario":
				this.tomarRespuestaUsuario();
				break;

			default:
				respuestaSistema = "Ingreso incorrecto";
				break;
			}
			
			this.escribirRespuestaDelSistema(respuestaSistema);
			System.out.println(respuestaSistema);
			
		} catch (Exception ex) {
			this.escribirRespuestaDelSistema(ex.getMessage());
			System.out.println(ex.getMessage());
		}
		
	}
	
	private void cleanTextFields() {
		nombreTextField.setText("");
		libroTextField.setText("");
		libreriaTextField.setText("0");
		logginTextField.setText("");
	}
	
	private void disabledTextFieldsAndBtnEnviarRespuesta() {
		nombreTextField.setEnabled(false);
		libroTextField.setEnabled(false);
		libreriaTextField.setEnabled(false);
		logginTextField.setEnabled(false);
		
		btnEnviarRespuesta.setEnabled(false);
	}
	
	private String alquilarLibro() {
		try {
			this.cleanTextFields();
			this.btnEnviarRespuesta.setEnabled(true);
			
			String res = "";
			this.nombreTextField.setEnabled(true);
			this.libroTextField.setEnabled(true);
			this.opcionElegida = Opcion.retirarLibro;
			
			res = "Intentando retirar libro.";
			
			return res;
		} catch (Exception e) {
			throw e;
		}
		
	}
	
	private String agregarLibro() {
		try {
			this.cleanTextFields();
			this.btnEnviarRespuesta.setEnabled(true);
			
			String res = "Intentando agregar nuevo libro.";
			this.libreriaTextField.setEnabled(true);
			this.libroTextField.setEnabled(true);
			this.opcionElegida = Opcion.agregarLibro;
			
			return res;
		} catch (Exception e) {
			throw e;
		}
		
	}
	
	private String agregarCliente() {
		try {
			this.cleanTextFields();
			this.btnEnviarRespuesta.setEnabled(true);
			
			String res = "Intentando subscribir cliente";
			this.nombreTextField.setEnabled(true);
			this.opcionElegida = Opcion.subscribirCliente;
			
			return res;
		} catch (Exception e) {
			throw e;
		}
		
	}
	
	private String quitarCliente() {
		try {
			this.cleanTextFields();
			this.btnEnviarRespuesta.setEnabled(true);
			
			String res = "Intentando des-subscribir cliente";
			this.nombreTextField.setEnabled(true);
			this.opcionElegida = Opcion.desSubscribirCliente;
			
			return res;
		} catch (Exception e) {
			throw e;
		}
		
	}
	
	private String listarLibros() {
		try {
			this.cleanTextFields();
			this.opcionElegida = Opcion.listarLibros;
			this.enviarRespuestaAlControlador();
			
			return this.respuestaSistema;
		} catch (Exception e) {
			throw e;
		}
	}
	
	private String listarCredenciales() {
		try {
			this.cleanTextFields();
			this.opcionElegida = Opcion.listarCredenciales;
			this.controlador.procesarEleccion();
			
			return this.respuestaSistema;
		} catch (Exception e) {
			throw e;
		}
	}
	
	private String devolverLibro() {
		try {
			this.cleanTextFields();
			this.btnEnviarRespuesta.setEnabled(true);
			
			String res = "Intentando devolver libro.";
			this.nombreTextField.setEnabled(true);
			this.libroTextField.setEnabled(true);
			this.opcionElegida = Opcion.devolverLibro;
			
			return res;
		} catch (Exception e) {
			throw e;
		}
		
	}
	
	private String agregarAdmin() {
		try {
			this.cleanTextFields();
			this.btnEnviarRespuesta.setEnabled(true);
			
			String res = "Intentando agregar nuevo admin.";
			this.nombreTextField.setEnabled(true);
			this.opcionElegida = Opcion.agregarAdmin;
			
			return res;
		} catch (Exception e) {
			throw e;
		}
	}
	
	private String habilitarDeshabilitarCliente() {
		try {
			this.cleanTextFields();
			this.btnEnviarRespuesta.setEnabled(true);
			
			String res = "Intentando cambiar estado habilitacion.";
			this.nombreTextField.setEnabled(true);
			this.opcionElegida = Opcion.habilitarDeshabilitarCredencial;
			
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
	
	private void ejecutarLoggin() {
		try {
			String nombreCredencial = this.logginTextField.getText();
			controlador.loggin(nombreCredencial);
		} catch (Exception e) {
			throw new RuntimeException("No se pudo completar el loggin, porque: "+e.getMessage());
		}
	}
	
	private void tomarRespuestaUsuario() {
		try {
			
			if(isLogged) {
				this.enviarRespuestaAlControlador();
			} else {
				ejecutarLoggin();
			}
			
		} catch (Exception e) {
			throw e;
		}
	}
	
	private void enviarRespuestaAlControlador() {
		try {
			String rta = this.nombreTextField.getText();
			rta.isBlank();
			RequestDto infoDto = new RequestDto();
			infoDto.setNombreLibro(this.libroTextField.getText());
			infoDto.setNombreCredencial(this.nombreTextField.getText());
			infoDto.setNumeroLibreria(Integer.parseInt(this.libreriaTextField.getText()));
			this.requestInfo = infoDto;
			
			this.controlador.procesarEleccion();
			this.disabledTextFieldsAndBtnEnviarRespuesta();
			
		} catch (Exception e) {
			throw e;
		}
	}
	
	private void activarBotonera() {
		agregarLibro.setEnabled(true);
		alquilarLibro.setEnabled(true);
		devolverLibro.setEnabled(true);
		listarLibros.setEnabled(true);
		subscribirCredencial.setEnabled(true);
		desSubscribirCredencial.setEnabled(true);
		listarCredenciales.setEnabled(true);
		habilitarCredencial.setEnabled(true);
		agregarAdmin.setEnabled(true);
		salir.setEnabled(true);
	}
}
