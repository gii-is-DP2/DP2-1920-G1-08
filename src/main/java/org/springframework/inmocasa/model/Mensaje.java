package org.springframework.inmocasa.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Mensaje extends BaseEntity{
	
	@NotBlank
	private String destinatario;
	
	@NotBlank
	private String receptor;
	
	@NotBlank
	private String asunto;
	
	@NotBlank
	private String cuerpo;
	
	@NotNull
	@Valid
	@ManyToOne(optional=false)
	private Usuario usuario;

	public String getDestinatario() {
		return destinatario;
	}

	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}

	public String getReceptor() {
		return receptor;
	}

	public void setReceptor(String receptor) {
		this.receptor = receptor;
	}

	public String getAsunto() {
		return asunto;
	}

	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

	public String getCuerpo() {
		return cuerpo;
	}

	public void setCuerpo(String cuerpo) {
		this.cuerpo = cuerpo;
	}
	

}
