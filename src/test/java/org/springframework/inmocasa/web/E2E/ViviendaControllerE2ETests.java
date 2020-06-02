package org.springframework.inmocasa.web.E2E;

import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.inmocasa.InmocasaApplication;
import org.springframework.inmocasa.model.Vivienda;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
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
	void testTodasViviendas() throws Exception {
		mockMvc.perform(get("/viviendas/allNew")).andExpect(view().name("viviendas/listNewViviendas"))
				.andExpect(model().attributeExists("viviendas"));
	}

	@WithMockUser(username = "gilmar", authorities = { "gilmar" })
	@Test
	void testUpdateVivienda() throws Exception {
		mockMvc.perform(get("/viviendas/{viviendaId}/edit", TEST_VIVIENDA_ID)).andExpect(status().isOk())
				.andExpect(view().name("viviendas/editVivienda"));
	}

	// HU-008: Filtrar vivienda
	@WithMockUser(value = "inmocasa")
	@Test
	void testshowListViviendaFiltroOk() throws Exception {
		mockMvc.perform(get("/viviendas/allNewFiltros").with(csrf()).param("min", "100").param("max", "3000")
				.param("zona", "Cerro Amate")).andExpect(status().isOk()).andExpect(view().name("viviendas/listNewViviendas"));
	}

	// HU-008: Filtrar vivienda
	@WithMockUser(value = "inmocasa")
	@Test
	void testshowListViviendaFiltroNotOk() throws Exception {
		mockMvc.perform(get("/viviendas/allNewFiltros").with(csrf()).param("min", "100").param("max", "20"))
		.andExpect(model().attribute("error", "El precio mínimo debe ser menor al precio máximo."))
		.andExpect(status().isOk()).andExpect(view().name("viviendas/listNewViviendas"));
	}

	@DisplayName("Prueba borrar anuncio")
	@WithMockUser(value = "gilmar", authorities = { "propietario" })
	@Test
	void testDeleteViviendaOk() throws Exception {
		mockMvc.perform(get("/viviendas/delete/{viviendaId}", 1)).andExpect(status().isOk());
	}

	@DisplayName("Prueba no borrar anuncio")
	@Test
	void testDeleteViviendaNotOk() throws Exception {
		mockMvc.perform(get("/viviendas/delete/{viviendaId}", 1)).andExpect(status().is3xxRedirection());
	}
}
