package main.commons;

public class RequestDto {
	private String nombreLibro;
	private String nombreCredencial;
	private Integer numeroLibreria;
	
	public String getNombreLibro() {
		return nombreLibro;
	}
	public void setNombreLibro(String nombreLibro) {
		this.nombreLibro = nombreLibro;
	}
	public String getNombreCredencial() {
		return nombreCredencial;
	}
	public void setNombreCredencial(String nombreCredencial) {
		this.nombreCredencial = nombreCredencial;
	}
	public Integer getNumeroLibreria() {
		return numeroLibreria;
	}
	public void setNumeroLibreria(Integer numeroLibreria) {
		this.numeroLibreria = numeroLibreria;
	}
	
}
