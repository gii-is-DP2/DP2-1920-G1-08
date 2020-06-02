package org.springframework.inmocasa.web.E2E;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class ValoracionControllerE2ETests {
	
	@Autowired
	private MockMvc mockMvc;
	
	@WithMockUser(value = "bravo9", authorities = { "cliente" })
	@Test
	@DisplayName("Prueba en la que no se crea una valoración porque ya existe")
	void testCreateValoracionNotOk() throws Exception {
		mockMvc.perform(get("/valoracion/{visitaId}/new",1)).andExpect(status().isOk()).andExpect(model().attribute("error","Ya ha realizado una valoración a esta vivienda."))
				.andExpect(view().name("users/visitas"));
	}
	
	@WithMockUser(value = "bravo9", authorities = { "cliente" })
	@Test
	@DisplayName("Prueba en la que se crea una valoración")
	void testCreateValoracionOk() throws Exception {
		mockMvc.perform(get("/valoracion/{visitaId}/new",2)).andExpect(status().isOk()).andExpect(model().attributeExists("valoracion"))
				.andExpect(view().name("/visita/valoracion/createValoracionForm"));
	}

}
