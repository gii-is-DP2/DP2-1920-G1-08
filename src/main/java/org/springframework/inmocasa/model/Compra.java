package org.springframework.inmocasa.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.inmocasa.model.enums.Estado;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Compra extends BaseEntity{

	@NotNull
	private Integer precioFinal;
	
	@NotNull
	private Estado estado;
	
	private String comentario;
	
	@NotNull
	@Valid
	@ManyToOne(optional=false)
	private Cliente cliente;
	
	@NotNull
	@Valid
	@OneToOne(optional=false)
	private Vivienda vivienda;

	
}
