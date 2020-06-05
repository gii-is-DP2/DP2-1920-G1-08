package org.springframework.inmocasa.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.time.LocalDate;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.junit.jupiter.api.Test;
import org.springframework.inmocasa.model.enums.Genero;

public class ViviendaTests extends ValidatorTests {

	// Vivienda OK
	@Test
	void shouldViviendaValidateWhenCorrect() {
		Vivienda vivienda = new Vivienda();
		vivienda.setId(1);
		vivienda.setTitulo("Piso en venta en ocho de marzo s/n");
		vivienda.setDireccion("Calle Ocho de Marzo s/n");
		vivienda.setZona("Cerro Amate");
		vivienda.setFechaPublicacion(LocalDate.of(2020, 01, 20));
		vivienda.setPrecio(2260);
		vivienda.setAmueblado(true);
		vivienda.setCaracteristicas("Caracteristicas");
		vivienda.setHorarioVisita("Martes de 9:00 a 13:00");
		
		Propietario prop = new Propietario();
		prop.setId(8);
		prop.setNombre("John");
		prop.setApellidos("Doe");
		prop.setDni("46900025N");
		prop.setEsInmobiliaria(false);
		prop.setGenero(Genero.MASCULINO);
		prop.setFechaNacimiento(LocalDate.of(1976, 6, 12));
		prop.setUsername("john123");
		prop.setPassword("john123");
		vivienda.setPropietario(prop);


		Validator validator = createValidator();
		Set<ConstraintViolation<Vivienda>> constraint = validator.validate(vivienda);

		assertThat(constraint.size()).isEqualTo(0);
	}

	@Test
	void shouldViviendaWithoutPrice() {
		Vivienda v = new Vivienda();
		v.setId(1);
		v.setTitulo("Vivienda en los Remedios");
		v.setAmueblado(false);
		v.setCaracteristicas("4 dormitorios");
		v.setComentario("");
		v.setDimensiones(200 ^ 2);
		v.setEquipamiento("");
		v.setDireccion("Calle Asunción s/n");
		v.setZona("Remedios");
		v.setFechaPublicacion(LocalDate.of(2020, 01, 20));
		v.setHorarioVisita("Martes de 9:00 a 13:00");
		Validator validator = createValidator();
		Set<ConstraintViolation<Vivienda>> constraint = validator.validate(v);

		assertNotEquals(0, constraint.size());

	}
}
