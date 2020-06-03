package org.springframework.inmocasa.model;


import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
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
public class Denuncia extends BaseEntity {
	
	@NotNull
	private String justificacion;
	
	@Valid
	@ManyToOne(optional=false)
	private Vivienda vivienda;

}
