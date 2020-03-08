package org.springframework.inmocasa.model;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
public class Compra extends BaseEntity{

	@NotNull
	private Integer precioFinal;
	
	@NotNull
	private Estado estado;
	
	private String comentario;

	public Integer getPrecioFinal() {
		return precioFinal;
	}

	public void setPrecioFinal(Integer precioFinal) {
		this.precioFinal = precioFinal;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
}
