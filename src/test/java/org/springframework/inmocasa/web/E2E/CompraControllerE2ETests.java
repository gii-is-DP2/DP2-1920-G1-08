package org.springframework.inmocasa.web.E2E;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.inmocasa.InmocasaApplication;
import org.springframework.inmocasa.model.Cliente;
import org.springframework.inmocasa.model.Compra;
import org.springframework.inmocasa.model.Mensaje;
import org.springframework.inmocasa.model.Vivienda;
import org.springframework.inmocasa.model.enums.Estado;
import org.springframework.inmocasa.service.ClienteService;
import org.springframework.inmocasa.service.ViviendaService;
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
	private ClienteService clienteService;

	@Autowired
	private ViviendaService viviendaService;

	@Autowired
	private MockMvc mockMvc;

	@WithMockUser(username = "admin1", authorities = "admin")
	@Test
	void testProcessRechazarCompra() throws Exception {
		this.mockMvc.perform(get("/compras/{viviendaId}/rechazar", TEST_VIVIENDA_ID))
				.andExpect(status().is3xxRedirection()).andExpect(view().name("redirect:/viviendas/ofertadas"));
	}

	@WithMockUser(username = "admin1", authorities = { "admin" })
	@Test
	void testShowCompra() throws Exception {
		Cliente c= clienteService.findClienteById(TEST_CLIENTE_ID);
		c.setMensaje(new ArrayList<Mensaje>());
		Vivienda v = viviendaService.findViviendaId(TEST_VIVIENDA_ID);
		this.mockMvc.perform(get("/compras/{viviendaId}", TEST_VIVIENDA_ID)).andExpect(status().isOk())
				.andExpect(model().attribute("compra", Matchers.hasProperty("precioFinal", Matchers.is(215.000))))
				.andExpect(model().attribute("compra", Matchers.hasProperty("estado", Matchers.is(Estado.ACEPTADO))))
				.andExpect(model().attribute("compra", Matchers.hasProperty("cliente", Matchers.is(c))))
				.andExpect(model().attribute("compra", Matchers.hasProperty("vivienda",Matchers.is(v))))
				.andExpect(view().name("compras/showCompraDetails"));
	
}}
