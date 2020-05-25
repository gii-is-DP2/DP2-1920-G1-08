package org.springframework.inmocasa.web.E2E;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.inmocasa.InmocasaApplication;
import org.springframework.inmocasa.model.Cliente;
import org.springframework.inmocasa.model.Propietario;
import org.springframework.inmocasa.service.ClienteService;
import org.springframework.inmocasa.service.PropietarioService;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.paypal.base.rest.APIContext;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@ContextConfiguration(classes = { InmocasaApplication.class })
public class MensajeControllerE2ETests {

	private static int TEST_CLIENTE_ID = 9;

	private static int TEST_PROPIETARIO_ID = 1;

	@MockBean
	APIContext apiContext;
	
	@Autowired
	private PropietarioService propietarioService;

	@Autowired
	private ClienteService clienteService;



	@Autowired
	private MockMvc mockMvc;

	@WithMockUser(username = "gilmar", password = "gilmar", authorities = { "propietario" })
	@Test
	void testInitCreationForm() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/mensajes/new")).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(view().name("mensajes/editMensaje"))
				.andExpect(MockMvcResultMatchers.model().attributeExists("mensaje"));
	}

	@WithMockUser(username = "gilmar", authorities = { "propietario" })
	@Test
	void testProcessCreationFormSuccess() throws Exception {
		Cliente c = clienteService.findClienteById(TEST_CLIENTE_ID);
		Propietario p = propietarioService.findPropietarioById(TEST_PROPIETARIO_ID);
		mockMvc.perform(post("mensajes/save").with(csrf()).param("asunto", "Hola").param("cuerpo", "Amigo"))
				.andExpect(status().isOk()).andExpect(view().name("/mensajes/editMensaje"));
	}

}
