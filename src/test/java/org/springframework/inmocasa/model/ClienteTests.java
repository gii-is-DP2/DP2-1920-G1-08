package org.springframework.inmocasa.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.time.LocalDate;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.junit.jupiter.api.Test;
import org.springframework.inmocasa.model.enums.Genero;

public class ClienteTests extends ValidatorTests {
	@Test
	void shouldClienteValidateWhenCorrect() {
		Cliente client = new Cliente();
		client.setId(10);
		client.setNombre("John");
		client.setApellidos("Doe");
		client.setDni("46900025N");
		client.setGenero(Genero.MASCULINO);
		client.setFechaNacimiento(LocalDate.of(1976, 6, 12));
		client.setUsername("john123");
		client.setPassword("john123");

		Validator validator = createValidator();
		Set<ConstraintViolation<Cliente>> constraint = validator.validate(client);

		assertThat(constraint.size()).isEqualTo(0);
	}

	@Test
	void shouldClienteWithoutPassword() {
		Cliente client = new Cliente();
		client.setId(10);
		client.setNombre("John");
		client.setApellidos("Doe");
		client.setDni("46900025N");
		client.setGenero(Genero.MASCULINO);
		client.setFechaNacimiento(LocalDate.of(1976, 6, 12));
		client.setUsername("john123");
		Validator validator = createValidator();
		Set<ConstraintViolation<Cliente>> constraint = validator.validate(client);

		assertNotEquals(0, constraint.size());

	}
}
