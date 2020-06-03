package org.springframework.inmocasa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

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
	@Size(min = 1, max = 50)
	private String asunto;
	
	@NotBlank
	@Size(min = 1, max = 150)
	private String cuerpo;
	
	@Column(name="emisor_id")
	private Integer emisorId;
	
	@Column(name="receptor_id")
	private Integer receptorId;
	
	@Valid
	@ManyToOne(optional=false,fetch = FetchType.EAGER)
	@JoinColumn(name="client")
	protected Cliente client;
	
	@Valid
	@ManyToOne(optional=false,fetch = FetchType.EAGER)
	@JoinColumn(name="prop")
	protected Propietario prop;

	
	

}
