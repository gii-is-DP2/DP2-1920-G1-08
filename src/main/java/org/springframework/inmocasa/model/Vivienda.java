package org.springframework.inmocasa.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.URL;
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
public class Vivienda extends BaseEntity{
	
	@NotNull
	private String titulo;
	
	private LocalDate fechaPublicacion;

	@NotBlank
	private String direccion;

	@NotBlank
	private String zona;

	@NotNull
	private Integer precio;

	private Integer dimensiones;

	private Boolean amueblado;

	private String planta;

	@URL
	private String foto;

	private String caracteristicas;

	private String equipamiento;

	private Boolean publicitado;
	
	@Column(name = "comentario")
    private String comentario;
	
	@Column(name="horario_visita")

	private String horarioVisita;
	
	@Transient
	private boolean fav;

	@NotNull
	@Valid
	@ManyToOne
	private Propietario propietario;

}