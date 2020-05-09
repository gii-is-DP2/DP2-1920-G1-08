package org.springframework.inmocasa.model;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

import javax.persistence.Column;


@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Cliente extends Usuario {
	
	
	@OneToMany(mappedBy="emisor")
	protected List<Mensaje> mensajeEnviado;
	
	@ManyToMany
	protected List<Vivienda> favoritas;
	
}
