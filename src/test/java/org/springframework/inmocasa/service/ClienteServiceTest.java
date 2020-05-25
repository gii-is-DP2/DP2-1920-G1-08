package org.springframework.inmocasa.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.inmocasa.InmocasaApplication;
import org.springframework.inmocasa.model.Cliente;
import org.springframework.inmocasa.model.enums.Genero;
import org.springframework.inmocasa.service.ClienteService;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
@AutoConfigureTestDatabase(replace=Replace.NONE)
public class ClienteServiceTest {

	@Autowired 
	ClienteService cs;
	
	@Test
	@DisplayName("Prueba registro de nuevo cliente")
	void createCliente() {
		Cliente cl = new Cliente();
		cl.setId(99);
		cl.setNombre("Antonio");
		cl.setApellidos("Fernandez");
		cl.setDni("46900025A");
		cl.setGenero(Genero.MASCULINO);
		cl.setFechaNacimiento(LocalDate.of(1978, 10, 12));
		cl.setUsername("ant1978");
		cl.setPassword("ant1978");
		
		cs.saveCliente(cl);
		Cliente c2 = cs.findByUsername(cl.getUsername());
		assertThat(c2 != null && c2.getUsername().equals(cl.getUsername()));
	}
}
