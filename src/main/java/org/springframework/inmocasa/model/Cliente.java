package org.springframework.inmocasa.model;

import javax.persistence.Entity;
import javax.persistence.Column;

@Entity
public class Cliente extends Usuario {
	
	@Column(name="favoritos")
	protected String favoritos;

	public String getFavoritos() {
		return favoritos;
	}

	public void setFavoritos(String favoritos) {
		this.favoritos = favoritos;
	}
}
