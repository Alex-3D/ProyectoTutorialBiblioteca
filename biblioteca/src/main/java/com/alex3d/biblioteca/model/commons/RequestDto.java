package com.alex3d.biblioteca.model.commons;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RequestDto {
	@JsonProperty("nombreLibro")
	private String nombreLibro;
	@JsonProperty("nombreCredencial")
	private String nombreCredencial;
	@JsonProperty("numeroLibreria")
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
