package org.springframework.inmocasa.model;

import javax.persistence.Entity;

import org.hibernate.validator.constraints.URL;

import com.sun.istack.NotNull;

@Entity
public class Habitacion extends BaseEntity {
	
	@NotNull
	private TipoHabitacion tipoHabitación;

	private Integer numCamas;
	
	private String caracteristicas;
	
	@URL
	private String foto;

	public TipoHabitacion getTipoHabitación() {
		return tipoHabitación;
	}

	public void setTipoHabitación(TipoHabitacion tipoHabitación) {
		this.tipoHabitación = tipoHabitación;
	}

	public Integer getNumCamas() {
		return numCamas;
	}

	public void setNumCamas(Integer numCamas) {
		this.numCamas = numCamas;
	}

	public String getCaracteristicas() {
		return caracteristicas;
	}

	public void setCaracteristicas(String caracteristicas) {
		this.caracteristicas = caracteristicas;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}
}
