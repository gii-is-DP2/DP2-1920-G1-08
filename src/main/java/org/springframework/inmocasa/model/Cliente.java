package org.springframework.inmocasa.model;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
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
	
	
	@OneToMany(mappedBy="client")
	@ElementCollection(fetch = FetchType.LAZY)
	protected List<Mensaje> mensaje;
	
	@ManyToMany()
	protected List<Vivienda> favoritas;
	
}
