package org.springframework.inmocasa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
public class Propietario extends Usuario {
	
	@Column(name="es_inmobiliaria")
	@NotNull
	private Boolean esInmobiliaria;
	
	@Column(name="inmobiliaria")
	private String inmobiliaria;
	
	@Column(name="cif")
	private String cif;

	public Boolean getEsInmobiliaria() {
		return esInmobiliaria;
	}

	public void setEsInmobiliaria(Boolean esInmobiliaria) {
		this.esInmobiliaria = esInmobiliaria;
	}

	public String getInmobiliaria() {
		return inmobiliaria;
	}

	public void setInmobiliaria(String inmobiliaria) {
		this.inmobiliaria = inmobiliaria;
	}

	public String getCif() {
		return cif;
	}

	public void setCif(String cif) {
		this.cif = cif;
	}

	
	
}
