package org.springframework.inmocasa.web.E2E;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.inmocasa.InmocasaApplication;
import org.springframework.inmocasa.model.enums.Estado;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@ContextConfiguration(classes = { InmocasaApplication.class })
public class CompraControllerE2ETests {

	int TEST_VIVIENDA_ID = 1;
	int TEST_CLIENTE_ID = 9;

	@Autowired
	private MockMvc mockMvc;

	@WithMockUser(username = "admin", authorities = "admin")
	@Test
	void testProcessRechazarCompra() throws Exception {
		this.mockMvc.perform(get("/compras/{viviendaId}/rechazar", TEST_VIVIENDA_ID))
				.andExpect(status().is3xxRedirection()).andExpect(view().name("redirect:/viviendas/ofertadas"));
	}

	@WithMockUser(username = "admin", authorities = { "admin" })
	@Test
	void testShowCompra() throws Exception {
		this.mockMvc.perform(get("/compras/{viviendaId}", 1)).andExpect(status().isOk())
				.andExpect(model().attribute("compras", Matchers.hasProperty("precioFinal", Matchers.is(215000))))
				.andExpect(model().attribute("compras", Matchers.hasProperty("estado", Matchers.is(Estado.ACEPTADO))))
				.andExpect(view().name("compras/showCompraDetails"));

	}
}
