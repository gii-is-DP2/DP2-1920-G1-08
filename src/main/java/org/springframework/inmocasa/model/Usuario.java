/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.inmocasa.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.inmocasa.model.enums.Genero;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Simple JavaBean domain object representing an person.
 *
 * @author Ken Krebs
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@MappedSuperclass
public class Usuario extends BaseEntity {

	@Column(name="dni")
	@NotEmpty
	protected String dni;
	
	@Column(name = "nombre")
	@NotEmpty
	protected String nombre;

	@Column(name = "apellidos")
	protected String apellidos;

	@Column(name="genero")
	@NotNull
	protected Genero genero;
	
	@Column(name="telefono")
	protected String telefono;
	
	@DateTimeFormat(pattern="yyyy/MM/dd")
	@Column(name="fecha_nacimiento")
	protected LocalDate fechaNacimiento;
	
	@Column(name="email")
	protected String email;
	
	@Column(name="username")
	@NotEmpty
	protected String username;
	
	@Column(name="password")
	@NotEmpty
	protected String password;


	

	

}
