package org.springframework.inmocasa.web.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.inmocasa.model.Valoracion;
import org.springframework.inmocasa.model.Visita;
import org.springframework.inmocasa.service.ClienteService;
import org.springframework.inmocasa.service.VisitaService;
import org.springframework.inmocasa.service.ViviendaService;
import org.springframework.inmocasa.web.VisitaController;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.MapBindingResult;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class VisitaControllerIntegrationTests {
	
	private static final int TEST_VISITA_ID_Ok = 2;

	
	@Autowired
	VisitaService visitaService;
	
	@Autowired 
	VisitaController visitaController;
	
	@Autowired
	ClienteService clienteService;
	
	@Autowired
	ViviendaService viviendaService;
	
	@WithMockUser(value = "bravo9", authorities = { "Cliente" })
	@Test
    @DisplayName("Prueba en la que se accede al formulario de una visita ")
	void testCreateVisitaOk() throws Exception {
		ModelMap model = new ModelMap();
		String view = visitaController.newVisita(TEST_VISITA_ID_Ok, model);
		assertEquals(view, "viviendas/visitas/createOrUpdateVisita");
	}
	
	@WithMockUser(value = "bravo9", authorities = { "Cliente" })
	@Test
    @DisplayName("Prueba en la que se va a guardar una visita ")
	void testSaveVisita() throws Exception {
		ModelMap model = new ModelMap();
		Visita newVisita = new Visita();
		
		newVisita.setCliente(clienteService.findByUsername("bravo9"));
		newVisita.setFecha(LocalDate.now());
		newVisita.setLugar("Sevilla");
		newVisita.setVivienda(viviendaService.findViviendaId(3));
		BindingResult bindingResult = new MapBindingResult(Collections.emptyMap(), "");
		String view = visitaController.saveVisita(newVisita, bindingResult, model);
		assertEquals(view, "users/visitas");
		assertNotNull(model.get("success"));
	}

	
}