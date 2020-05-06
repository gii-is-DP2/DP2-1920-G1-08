package org.springframework.inmocasa.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Cliente extends Usuario {
	
	@Column(name="favoritos")
	protected String favoritos;
	
	@OneToMany(mappedBy="client")
	protected List<Mensaje> mensaje;
}
