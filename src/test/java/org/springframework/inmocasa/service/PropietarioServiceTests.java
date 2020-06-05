package org.springframework.inmocasa.service;

import java.time.LocalDate;
import java.util.Collection;

import static org.junit.Assert.assertTrue;

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

		Propietario propietario = new Propietario();
		// propietario.setId(20);
		propietario.setNombre("Luis");
		propietario.setApellidos("Melero");
		propietario.setDni("123456789A");
		propietario.setGenero(Genero.MASCULINO);
		propietario.setFechaNacimiento(LocalDate.of(1998, 10, 12));
		propietario.setUsername("lj2386");
		propietario.setPassword("lj2386");
		propietario.setEsInmobiliaria(true);

		this.propietarioService.savePropietario(propietario);
		Collection<Propietario> propietarios = this.propietarioService.findAll();

		assertTrue(propietarios.contains(propietario));

	}

	// El propietario no se guarda porque ya existe uno con el mismo username
	@Test
	void shouldNotCreateAndSavePropietario() {
		Collection<Propietario> propietarios = this.propietarioService.findAll();

		Propietario propietario = new Propietario();
		// propietario.setId(13);
		propietario.setNombre("Antonio");
		propietario.setApellidos("Fernandez");
		propietario.setDni("46900025A");
		propietario.setGenero(Genero.MASCULINO);
		propietario.setFechaNacimiento(LocalDate.of(1978, 10, 12));
		propietario.setUsername("santiago");
		propietario.setPassword("alejandra");
		propietario.setEsInmobiliaria(true);


		this.propietarioService.savePropietario(propietario);

		assertTrue(!propietarios.contains(propietario));

	}

	// Se encuentran la propietario por el id y este existe.
	@Test
	void shouldFindPropietarioById() {
		Collection<Propietario> propietarios = this.propietarioService.findAll();
		Propietario c = this.propietarioService.findPropietarioById(1);
		assertTrue(propietarios.contains(c));

	}

	// No se encuentra el propietario porque el id no existe.
	@Test
	void shouldNoFindPropietarioById() {
		Propietario c = this.propietarioService.findPropietarioById(16);
		assertTrue(c == null);
	}
}
