package org.springframework.inmocasa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
public class Mensaje extends BaseEntity{
	

	@NotBlank
	private String asunto;
	
	@NotBlank
	private String cuerpo;
	
	@Column(name="emisor_id")
	private Integer emisorId;
	
	@Column(name="receptor_id")
	private Integer receptorId;
	
	@Valid
	@ManyToOne(optional=false)
	@JoinColumn(name="client")
	protected Cliente client;
	
	@Valid
	@ManyToOne(optional=false)
	@JoinColumn(name="prop")
	protected Propietario prop;

	
	

}
