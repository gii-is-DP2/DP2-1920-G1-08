package org.springframework.inmocasa.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.time.LocalDate;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.junit.jupiter.api.Test;
import org.springframework.inmocasa.model.enums.Estado;
import org.springframework.inmocasa.model.enums.Genero;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.junit.jupiter.api.Test;

public class CompraTests extends ValidatorTests {

	// Todo bien
	@Test
	void ShouldCompraValidateWhenCorrect() {

		Vivienda vivienda = new Vivienda();
		vivienda.setId(1);
		vivienda.setTitulo("Piso en venta en ocho de marzo s/n");
		vivienda.setDireccion("Calle Ocho de Marzo s/n");
		vivienda.setZona("Cerro Amate");
		vivienda.setFechaPublicacion(LocalDate.of(2020, 01, 20));
		vivienda.setPrecio(2260);
		vivienda.setAmueblado(true);

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

		Cliente cl = new Cliente();
		cl.setId(2);
		cl.setNombre("Antonio");
		cl.setApellidos("Fernandez");
		cl.setDni("46900025A");
		cl.setGenero(Genero.MASCULINO);
		cl.setFechaNacimiento(LocalDate.of(1978, 10, 12));
		cl.setUsername("ant1978");
		cl.setPassword("ant1978");

		Compra compra = new Compra();
		compra.setId(1);
		compra.setPrecioFinal(500);
		compra.setEstado(Estado.ACEPTADO);

		vivienda.setPropietario(prop);
		compra.setCliente(cl);
		compra.setVivienda(vivienda);
		
		Validator validator = createValidator();
		Set<ConstraintViolation<Compra>> constraint = validator.validate(compra);
		
		assertThat(constraint.size()).isEqualTo(0);
	}
	
	// Cliente = null
	@Test
	void shouldCompraWithoutCliente() {
		Vivienda vivienda = new Vivienda();
		vivienda.setId(1);
		vivienda.setTitulo("Piso en venta en ocho de marzo s/n");
		vivienda.setDireccion("Calle Ocho de Marzo s/n");
		vivienda.setZona("Cerro Amate");
		vivienda.setFechaPublicacion(LocalDate.of(2020, 01, 20));
		vivienda.setPrecio(2260);
		vivienda.setAmueblado(true);

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
		
		Compra compra = new Compra();
		compra.setId(1);
		compra.setPrecioFinal(500);
		compra.setEstado(Estado.ACEPTADO);

		vivienda.setPropietario(prop);
		compra.setCliente(null);
		compra.setVivienda(vivienda);
	
		Validator validator = createValidator();
		Set<ConstraintViolation<Compra>> constraint = validator.validate(compra);
		assertNotEquals(0, constraint.size());
	}
}
