package org.springframework.inmocasa.service;

import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.Collection;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.inmocasa.model.Cliente;
import org.springframework.inmocasa.model.enums.Genero;
import org.springframework.stereotype.Service;

import com.paypal.base.rest.APIContext;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class ClienteServiceTests {

	@Autowired
	protected ClienteService clienteService;

	@MockBean
	APIContext apiContext;

	// El cliente se crea y se guarda en el repositorio
	@Test
	void shouldCreateAndSaveCliente() {

		Cliente cliente = new Cliente();
		// cliente.setId(20);
		cliente.setNombre("Luis");
		cliente.setApellidos("Melero");
		cliente.setDni("123456789A");
		cliente.setGenero(Genero.MASCULINO);
		cliente.setFechaNacimiento(LocalDate.of(1998, 10, 12));
		cliente.setUsername("lj2386");
		cliente.setPassword("lj2386");

		this.clienteService.saveCliente(cliente);
		Collection<Cliente> clientes = this.clienteService.findAll();

		assertTrue(clientes.contains(cliente));

	}
	
	@ParameterizedTest
	@DisplayName("Prueba parametrizada de valores")
	@CsvSource({"1990","2020"})
	void shouldCreateAndSaveClienteParametrizada(int anyo) {

		Cliente cliente = new Cliente();
		// cliente.setId(20);
		cliente.setNombre("Luis");
		cliente.setApellidos("Melero");
		cliente.setDni("123456789A");
		cliente.setGenero(Genero.MASCULINO);
		LocalDate hoy = LocalDate.now();
		cliente.setFechaNacimiento(LocalDate.of(anyo, hoy.getMonth(), hoy.getDayOfMonth()));
		cliente.setUsername("lj2386");
		cliente.setPassword("lj2386");

		this.clienteService.saveCliente(cliente);
		Collection<Cliente> clientes = this.clienteService.findAll();

		assertTrue(clientes.contains(cliente));

	}

	// El cliente no se guarda porque ya existe uno con el mismo username
	@Test
	void shouldNotCreateAndSaveCliente() {
		Collection<Cliente> clientes = this.clienteService.findAll();

		Cliente cliente = new Cliente();
		// cliente.setId(13);
		cliente.setNombre("Antonio");
		cliente.setApellidos("Fernandez");
		cliente.setDni("46900025A");
		cliente.setGenero(Genero.MASCULINO);
		cliente.setFechaNacimiento(LocalDate.of(1978, 10, 12));
		cliente.setUsername("santiago");
		cliente.setPassword("alejandra");

		this.clienteService.saveCliente(cliente);

		assertTrue(!clientes.contains(cliente));

	}

	// Se encuentran la cliente por el id y este existe.
	@Test
	void shouldFindClienteById() {
		Collection<Cliente> clientes = this.clienteService.findAll();
		Cliente c = this.clienteService.findClienteById(10);
		assertTrue(clientes.contains(c));

	}

	// No se encuentra el cliente porque el id no existe.
	@Test
	void shouldNoFindClienteById() {
		Cliente c = this.clienteService.findClienteById(16);
		assertTrue(c == null);
	}
}
