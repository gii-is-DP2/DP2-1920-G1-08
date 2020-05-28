package org.springframework.inmocasa.model;

import java.util.List;


import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Cliente extends Usuario {

//	@OneToMany(mappedBy = "client",fetch = FetchType.EAGER)
//
//	protected List<Mensaje> mensaje;

	@ManyToMany(fetch = FetchType.EAGER)
	protected List<Vivienda> favoritas;

}
