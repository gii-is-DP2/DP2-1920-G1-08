package org.springframework.inmocasa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
public class Propietario extends Usuario {
	
	@Column(name="es_inmobiliaria")
	@NotNull
	protected Boolean esInmobiliaria;
	
	@Column(name="inmobiliaria")
	protected String inmobiliaria;
	
	@Column(name="cif")
	protected String cif;

}
