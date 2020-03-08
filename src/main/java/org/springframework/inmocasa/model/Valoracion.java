package org.springframework.inmocasa.model;

import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

public class Valoracion extends BaseEntity{
	
	@NotNull
	@Range(min = 0, max = 5)
	private Integer puntuacion;

	private String comentario;
	
	@NotNull
	@Valid
	@ManyToOne(optional=false)
	private Visita visita;

	public Integer getPuntuacion() {
		return puntuacion;
	}

	public void setPuntuacion(Integer puntuacion) {
		this.puntuacion = puntuacion;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
}
