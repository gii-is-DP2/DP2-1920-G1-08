package org.springframework.inmocasa.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
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
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class MensajeServiceTests {

	@MockBean
	APIContext apiContext;

	@Autowired
	private MensajeService mensajeService;

	// Se encuentra el mensaje por el id del emisor
	@Test
	@DisplayName("Se encuentra el mensaje por el ID del emisor")
	void shouldFindMensajeByEmisorId() {
		List<Mensaje> todos = (List<Mensaje>) mensajeService.findAll();
		int emisor = todos.get(0).getEmisorId();
		assertNotEquals(mensajeService.findMensajeByEmisorId(emisor), null);
	}

	// Se encuentra el mensaje por el id del receptor
	@Test
	@DisplayName("Se encuentra el mensaje por el ID del receptor")
	void shouldFindMensajeByReceptorId() {
		List<Mensaje> todos = (List<Mensaje>) mensajeService.findAll();
		int receptor = todos.get(0).getReceptorId();
		assertNotEquals(mensajeService.findMensajeByReceptor(receptor), null);
	}

	// No se encuentra el mensaje por el id del emisor porque no ha mandado mensaje
	// ese usuario
	@Test
	@DisplayName("No se encuentra el mensaje por el ID del emisor")
	void shouldNotFindMensajeByEmisorId() {
		assertThat(mensajeService.findMensajeByEmisorId(2)).isEmpty();
	}

	// No se encuentra el mensaje por el id del receptor porque no ha recibido
	// mensaje ese usuario
	@Test
	@DisplayName("Se encuentra el mensaje por el ID del receptor")
	void shouldNotFindMensajeByReceptorId() {
		assertThat(mensajeService.findMensajeByReceptor(2)).isEmpty();
	
	}
}
