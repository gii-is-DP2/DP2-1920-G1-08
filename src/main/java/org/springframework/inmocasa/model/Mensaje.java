package org.springframework.inmocasa.model;

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
	
//	@Valid
//	@ManyToOne(optional=false)
//	@JoinColumn(name="emisor")
//	protected Usuario emisor;
//	
//	@Valid
//	@ManyToOne(optional=false)
//	@JoinColumn(name="receptor")
//	protected Usuario receptor;

	
	

}
