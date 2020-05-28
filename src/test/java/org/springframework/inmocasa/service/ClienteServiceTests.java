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
		Collection<Cliente> clientes = this.clienteService.findAll();

		Cliente cliente = new Cliente();
		cliente.setId(2);
		cliente.setNombre("Antonio");
		cliente.setApellidos("Fernandez");
		cliente.setDni("46900025A");
		cliente.setGenero(Genero.MASCULINO);
		cliente.setFechaNacimiento(LocalDate.of(1978, 10, 12));
		cliente.setUsername("ant1978");
		cliente.setPassword("ant1978");

		this.clienteService.saveCliente(cliente);

		assertThat(clientes.contains(cliente));

	}

	// El cliente no se guarda porque ya existe uno con el mismo username
	@Test
	void shouldNotCreateAndSaveCliente() {
		Collection<Cliente> clientes = this.clienteService.findAll();

		Cliente cliente = new Cliente();
		cliente.setId(2);
		cliente.setNombre("Antonio");
		cliente.setApellidos("Fernandez");
		cliente.setDni("46900025A");
		cliente.setGenero(Genero.MASCULINO);
		cliente.setFechaNacimiento(LocalDate.of(1978, 10, 12));
		cliente.setUsername("ant1978");
		cliente.setPassword("ant1978");

		this.clienteService.saveCliente(cliente);

		assertThat(!clientes.contains(cliente));

	}

	// Se encuentran la cliente por el id y este existe.
	@Test
	void shouldFindClienteById() {
		Collection<Cliente> clientes = this.clienteService.findAll();
		Cliente c = this.clienteService.findClienteById(1);
		assertThat(clientes.contains(c));

	}

	// No se encuentra el cliente porque el id no existe.
	@Test
	void shouldNoFindClienteByViviendaId() {
		Cliente c = this.clienteService.findClienteById(10);
		assertThat(c == null);
	}
}
