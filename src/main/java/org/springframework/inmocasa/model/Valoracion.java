package org.springframework.inmocasa.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;
import org.springframework.inmocasa.model.enums.Genero;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
public class Valoracion extends BaseEntity{
	
	@NotNull
	@Range(min = 0, max = 5)
	private Integer puntuacion;

	private String comentario;
	
	@NotNull
	@Valid
	@ManyToOne(optional=false)
	private Visita visita;

}
