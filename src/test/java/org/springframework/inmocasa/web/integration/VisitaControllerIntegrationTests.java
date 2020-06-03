package org.springframework.inmocasa.web.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.inmocasa.service.VisitaService;
import org.springframework.inmocasa.web.VisitaController;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.ui.ModelMap;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class VisitaControllerIntegrationTests {
	
	private static final int TEST_VISITA_ID_Ok = 2;

	
	@Autowired
	VisitaService visitaService;
	
	@Autowired 
	VisitaController visitaController;
	
	@WithMockUser(value = "bravo9", authorities = { "Cliente" })
	@Test
    @DisplayName("Prueba en la que se accede al formulario de una visita ")
	void testCreateVisitaOk() throws Exception {
		ModelMap model = new ModelMap();
		String view = visitaController.newVisita(TEST_VISITA_ID_Ok, model);
		assertEquals(view, "viviendas/visitas/createOrUpdateVisita");
	}

	
}