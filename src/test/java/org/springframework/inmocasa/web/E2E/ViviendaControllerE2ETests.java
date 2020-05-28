package org.springframework.inmocasa.web.E2E;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.DisplayName;
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
	
	@DisplayName("Prueba filtro precio")
	@WithMockUser(value = "inmocasa")
	@Test
	void testshowListViviendaPrecioOk() throws Exception {
		mockMvc.perform(get("/viviendas/allNew").with(csrf()).param("precioMin", "100").param("precioMax", "10000"))
				.andExpect(status().isOk()).andExpect(view().name("viviendas/listNewViviendas"));
	}

	@DisplayName("Prueba filtro precio negativo")
	@WithMockUser(value = "inmocasa")
	@Test
	void testshowListViviendaNotPrecioOk() throws Exception {
		mockMvc.perform(get("/viviendas/allNew").with(csrf()).param("precioMin", "10").param("precioMax", "20"))
				.andExpect(model().attribute("error", "No se han encontrado viviendas en este rango de precio"))
				.andExpect(status().isOk()).andExpect(view().name("viviendas/listNewViviendas"));
	}

	@DisplayName("Prueba filtro zona")
	@WithMockUser(value = "inmocasa", authorities = "propietario")
	@Test
	void testshowListViviendaZonaOk() throws Exception {
		mockMvc.perform(get("/viviendas/allNew").with(csrf()).param("zona", "Cerro Amate")).andExpect(status().isOk())
				.andExpect(view().name("viviendas/listNewViviendas"));
	}

	@DisplayName("Prueba filtro zona negativo")
	@WithMockUser(value = "inmocasa")
	@Test
	void testshowListViviendaZonaNotOk() throws Exception {
		mockMvc.perform(get("/viviendas/allNew").with(csrf()).param("zona", "San Jerónimo"))
				.andExpect(model().attribute("error", "No se han encontrado viviendas en esta zona"))
				.andExpect(status().isOk()).andExpect(view().name("viviendas/listNewViviendas"));
	}

	// HU-008: Filtrar por habitaciones
	@DisplayName("Prueba filtro habitaciones")
	@WithMockUser(value = "inmocasa")
	@Test
	void testshowListViviendaHabitacionesOk() throws Exception {
		mockMvc.perform(get("/viviendas/allNew").with(csrf()).param("numhabitacion", "1")).andExpect(status().isOk())
				.andExpect(view().name("viviendas/listNewViviendas"));
	}

	@DisplayName("Prueba filtro habitaciones negativo")
	@WithMockUser(value = "inmocasa")
	@Test
	void testshowListViviendaHabitacionesNotOk() throws Exception {
		mockMvc.perform(get("/viviendas/allNew").with(csrf()).param("numhabitacion", "4"))
				.andExpect(model().attribute("error", "No se han encontrado viviendas con este número de habitaciones"))
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

