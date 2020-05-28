package org.springframework.inmocasa.web.E2E;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.inmocasa.InmocasaApplication;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class ClienteControllerE2ETests {

	int TEST_CLIENTE_ID = 1;

	@Autowired
	private MockMvc mockMvc;

	@WithMockUser(username = "admin", authorities = { "admin" })
	@Test
	void testUpdateClienteForm() throws Exception {
		mockMvc.perform(get("/clientes/{clienteId}/edit", TEST_CLIENTE_ID)).andExpect(status().isOk())
				.andExpect(view().name("clientes/registroClientes"));
	}

}
