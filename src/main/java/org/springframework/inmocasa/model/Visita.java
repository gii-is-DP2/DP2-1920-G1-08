package org.springframework.inmocasa.model;

import java.time.LocalDateTime;

import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

public class Visita {
	
	@NotNull
	@DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
	private LocalDateTime fecha;
	
	@NotNull
	private String lugar;
	
	@NotNull
	@Valid
	@ManyToOne(optional=false)
	private Cliente cliente;
	
	@NotNull
	@Valid
	@ManyToOne(optional=false)
	private Vivienda vivienda;

	public LocalDateTime getFecha() {
		return fecha;
	}

	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}

	public String getLugar() {
		return lugar;
	}

	public void setLugar(String lugar) {
		this.lugar = lugar;
	}

}
