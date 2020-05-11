package org.springframework.test.inmocasa.web.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.inmocasa.InmocasaApplication;
import org.springframework.inmocasa.model.Cliente;
import org.springframework.inmocasa.model.Propietario;
import org.springframework.inmocasa.model.Vivienda;
import org.springframework.inmocasa.model.enums.Genero;
import org.springframework.inmocasa.service.ClienteService;
import org.springframework.inmocasa.web.ClienteController;
import org.springframework.inmocasa.web.ViviendaController;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.MapBindingResult;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = { InmocasaApplication.class })
public class ClienteControllerIntegrationTests {

	private static final int TEST_CLIENTE_ID = 8;
	
	@Autowired
	ClienteController clienteController;
	
	@Autowired
	ClienteService clienteService;
	
	
	@WithMockUser(value = "alonso7", authorities = { "cliente" })
	@Test
	void testInitUpdateForm() throws Exception {
		ModelMap model = new ModelMap();
		String view = clienteController.editProfile(TEST_CLIENTE_ID,model);
		assertEquals(view, "clientes/registroClientes");
		assertNotNull(model.get("cliente"));
	}

	@WithMockUser(value = "alonso7", authorities = { "cliente" })
	@Test
	void testProcessUpdateFormSuccess() throws Exception {
		ModelMap model = new ModelMap();
		Cliente cliente = new Cliente();
		cliente.setId(TEST_CLIENTE_ID);
		cliente.setNombre("Gregorio");
		cliente.setApellidos("Martín");
		cliente.setDni("12345678D");
		cliente.setEmail("gregorio@gmail.com");
		cliente.setFavoritas(new ArrayList<Vivienda>());
		cliente.setGenero(Genero.MASCULINO);
		cliente.setUsername("gregorio23");
		cliente.setPassword("1235649b");
		cliente.setFechaNacimiento(LocalDate.of(1997, 04, 22));

		BindingResult bindingResult = new MapBindingResult(Collections.emptyMap(), "");

		String view = clienteController.processUpdateForm(cliente, bindingResult,
				model);

		assertEquals(view, "clientes/profile");
	}

}
