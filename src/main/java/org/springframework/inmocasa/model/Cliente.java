package org.springframework.inmocasa.model;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Cliente extends Usuario {
	
	@Column(name="favoritos")
	protected String favoritos;
	
	@OneToMany(mappedBy="emisor")
	protected List<Mensaje> mensajeEnviado;
}
