package org.springframework.inmocasa.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MensajeTests extends ValidatorTests{

	// Todo bien
	@Test
	@DisplayName("Se envía un mensaje correctamente")
	void shouldMensajeValidateWhenCorrect() {
		Mensaje m = new Mensaje();
		m.setAsunto("Prueba");
		m.setCuerpo("Del modelo");
		Validator validator = createValidator();
		Set<ConstraintViolation<Mensaje>> constraint = validator.validate(m);
		
		assertThat(constraint.size()).isEqualTo(0);
	}
	
	// Asunto-Cuerpo vacío
	@Test
	@DisplayName("No se envía el mensaje porque tiene un error")
	void shouldMensajeWithoutAsunto() {
		Mensaje m = new Mensaje();
		m.setAsunto("");
		m.setCuerpo("Cuerpo del mensaje");
		Validator validator = createValidator();
		Set<ConstraintViolation<Mensaje>> constraint = validator.validate(m);
		assertNotEquals(0, constraint.size());
		
	}
}
