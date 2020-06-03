package org.springframework.inmocasa.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity

public class Visita extends BaseEntity{
	
	@NotNull
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private LocalDate fecha;
	
	@NotNull
	private String lugar;
	
	@NotNull
	@Valid
	@ManyToOne(optional=false)
	private Cliente cliente;
	
	@NotNull
	@Valid
	@ManyToOne(optional=false)
	private Vivienda vivienda;

}
