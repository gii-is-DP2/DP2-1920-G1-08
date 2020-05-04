package org.springframework.inmocasa.model;

import javax.persistence.Entity;

//import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@Entity
public class Administrador extends Usuario {

}
