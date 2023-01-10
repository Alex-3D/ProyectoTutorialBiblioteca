package com.alex3d.biblioteca.model.commons;

import com.fasterxml.jackson.annotation.JsonProperty;

public class logginDto {
	@JsonProperty("nombreCredencial")
	private String nombreCredencial;

	public String getNombreCredencial() {
		return nombreCredencial;
	}
	
	public void setNombreCredencial(String nombreCredencial) {
		this.nombreCredencial = nombreCredencial;
	}
	
}
