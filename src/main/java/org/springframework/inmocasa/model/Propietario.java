package org.springframework.inmocasa.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Propietario extends Usuario {
	
	@Column(name="es_inmobiliaria")
	@NotNull
	private Boolean esInmobiliaria;
	
	@Column(name="inmobiliaria")
	private String inmobiliaria;
	
	@Column(name="cif")
	private String cif;
	
	@OneToMany(mappedBy="prop")
	protected List<Mensaje> mensaje;


}
