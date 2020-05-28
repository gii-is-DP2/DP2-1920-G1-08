package org.springframework.inmocasa.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.inmocasa.model.Propietario;
import org.springframework.inmocasa.model.enums.Genero;
import org.springframework.stereotype.Service;

import com.paypal.base.rest.APIContext;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class PropietarioServiceTests {

	@Autowired
	protected PropietarioService propietarioService;

	@MockBean
	APIContext apiContext;

	// El propietario se crea y se guarda en el repositorio
	@Test
	void shouldCreateAndSavePropietario() {
		Collection<Propietario> propietarios = this.propietarioService.findAll();

		Propietario propietario = new Propietario();
		propietario.setId(1);
		propietario.setNombre("Santiago");
		propietario.setApellidos("Martín");
		propietario.setDni("12345678D");
		propietario.setEmail("santimartinguay@gmail.com");

		propietario.setGenero(Genero.MASCULINO);
		propietario.setUsername("santiago");
		propietario.setPassword("santiago");
		propietario.setCif("12345678X");
		propietario.setEsInmobiliaria(true);
		propietario.setInmobiliaria("inmocasa");
		propietario.setFechaNacimiento(LocalDate.of(1998, 05, 31));

		this.propietarioService.savePropietario(propietario);

		assertThat(propietarios.contains(propietario));

	}

	// El propietario no se guarda porque ya existe uno con el mismo username
	@Test
	void shouldNotCreateAndSavePropietario() {
		Collection<Propietario> propietarios = this.propietarioService.findAll();

		Propietario propietario = new Propietario();
		propietario.setId(1);
		propietario.setNombre("Santiago");
		propietario.setApellidos("Martín");
		propietario.setDni("12345678D");
		propietario.setEmail("santimartinguay@gmail.com");

		propietario.setGenero(Genero.MASCULINO);
		propietario.setUsername("santiago");
		propietario.setPassword("santiago");
		propietario.setCif("12345678X");
		propietario.setEsInmobiliaria(true);
		propietario.setInmobiliaria("inmocasa");
		propietario.setFechaNacimiento(LocalDate.of(1998, 05, 31));

		this.propietarioService.savePropietario(propietario);

		assertThat(!propietarios.contains(propietario));

	}

	// Se encuentra el propietario por el id y este existe.
	@Test
	void shouldFindPropietarioById() {
		Collection<Propietario> propietarios = this.propietarioService.findAll();
		Propietario p = this.propietarioService.findPropietarioById(1);
		assertThat(propietarios.contains(p));

	}

	// No se encuentra el propietario porque el id no existe.
	@Test
	void shouldNoFindPropietarioByViviendaId() {
		Propietario p = this.propietarioService.findPropietarioById(10);
		assertThat(p == null);
	}
}
