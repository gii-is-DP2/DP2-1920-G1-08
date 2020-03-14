package org.springframework.inmocasa.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
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
public class Visita  extends BaseEntity{
	
	@NotNull
	@DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
	private LocalDateTime fecha;
	
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
