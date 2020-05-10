package org.springframework.test.inmocasa.web.E2E;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
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
@ContextConfiguration(classes = { InmocasaApplication.class })
public class ViviendaControllerE2ETests {

	@Autowired
	private MockMvc mockMvc;
	private static final int TEST_VIVIENDA_ID = 1;

	@WithMockUser(username = "gilmar", authorities = { "gilmar" })
	@Test
	void testListadoViviendas() throws Exception {
		mockMvc.perform(get("/viviendas/mis-viviendas")).andExpect(view().name("viviendas/misViviendas"))
				.andExpect(model().attributeExists("viviendas"));
	}

	@WithMockUser(username = "gilmar", authorities = { "gilmar" })
	@Test
	void testUpdateVivienda() throws Exception {
		mockMvc.perform(get("/viviendas/{viviendaId}/edit", TEST_VIVIENDA_ID)).andExpect(status().isOk())
				.andExpect(view().name("viviendas/editVivienda"));
	}

}
