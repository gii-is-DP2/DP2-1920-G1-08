package org.springframework.inmocasa.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;

import org.hibernate.validator.constraints.URL;
import org.springframework.inmocasa.model.enums.TipoHabitacion;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Habitacion extends BaseEntity {
	
	@NotNull
	private TipoHabitacion tipoHabitacion;

	private Integer numCamas;
	
	private String caracteristicas;
	
	@URL
	private String foto;
	
	@NotNull
	@Valid
	@ManyToOne(optional=false)
	private Vivienda vivienda;

}
