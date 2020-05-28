package org.springframework.inmocasa.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.inmocasa.model.Mensaje;
import org.springframework.stereotype.Service;

import com.paypal.base.rest.APIContext;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
@AutoConfigureTestDatabase(replace=Replace.NONE)
public class MensajeServiceTests {

	@MockBean
	APIContext apiContext;

	@Autowired
	private MensajeService mensajeService;

	// Se encuentra el mensaje por el id del emisor
	@Test
	void shouldFindMensajeByEmisorId() {
		List<Mensaje> todos = (List<Mensaje>) mensajeService.findAll();
		int emisor = todos.get(0).getEmisorId();
		assertThat(mensajeService.findMensajeByEmisorId(emisor) != null);
	}

	// Se encuentra el mensaje por el id del receptor
	@Test
	void shouldFindMensajeByReceptorId() {
		List<Mensaje> todos = (List<Mensaje>) mensajeService.findAll();
		int receptor = todos.get(0).getReceptorId();
		assertThat(mensajeService.findMensajeByReceptor(receptor) != null);
	}

	// No se encuentra el mensaje por el id del emisor porque no ha mandado mensaje
	// ese usuario
	@Test
	void shouldNotFindMensajeByEmisorId() {
		assertThat(mensajeService.findMensajeByEmisorId(2) == null);
	}

	// No se encuentra el mensaje por el id del receptor porque no ha recibido
	// mensaje ese usuario
	@Test
	void shouldNotFindMensajeByReceptorId() {
		assertThat(mensajeService.findMensajeByReceptor(2) == null);

	}
}
