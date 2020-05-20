package org.springframework.inmocasa.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.time.LocalDate;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.junit.jupiter.api.Test;
import org.springframework.inmocasa.model.enums.Genero;

public class PropietarioTests extends ValidatorTests {

	// Propietario OK
	@Test
	void shouldPropietarioValidateWhenCorrect() {
		Propietario prop = new Propietario();
		prop.setId(1);
		prop.setNombre("John");
		prop.setApellidos("Doe");
		prop.setDni("46900025N");
		prop.setEsInmobiliaria(false);
		prop.setGenero(Genero.MASCULINO);
		prop.setFechaNacimiento(LocalDate.of(1976, 6, 12));
		prop.setUsername("john123");
		prop.setPassword("john123");

		Validator validator = createValidator();
		Set<ConstraintViolation<Propietario>> constraint = validator.validate(prop);

		assertThat(constraint.size()).isEqualTo(0);
	}

	@Test
	void shouldPropietarioWithoutUsername() {
		Propietario prop = new Propietario();
		prop.setId(1);
		prop.setNombre("John");
		prop.setApellidos("Doe");
		prop.setDni("46900025N");
		prop.setEsInmobiliaria(false);
		prop.setGenero(Genero.MASCULINO);
		prop.setFechaNacimiento(LocalDate.of(1976, 6, 12));
		prop.setUsername("john123");
		Validator validator = createValidator();
		Set<ConstraintViolation<Propietario>> constraint = validator.validate(prop);

		assertNotEquals(0, constraint.size());

	}
}
