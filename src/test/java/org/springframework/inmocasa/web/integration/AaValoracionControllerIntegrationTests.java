package org.springframework.inmocasa.web.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Collections;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.inmocasa.model.Valoracion;
import org.springframework.inmocasa.model.Visita;
import org.springframework.inmocasa.service.VisitaService;
import org.springframework.inmocasa.web.ValoracionController;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.MapBindingResult;


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ValoracionControllerIntegrationTests {
	
	private static final int TEST_VISITA_ID_NotOk = 1;
	private static final int TEST_VISITA_ID_Ok = 2;
	private static final int TEST_VISITA_ID_Ok2 = 3;

	@Autowired
	ValoracionController valoracionController;
	
	@Autowired
	VisitaService visitaService;
	
	@WithMockUser(value = "bravo9", authorities = { "Cliente" })
	@Test
    @DisplayName("Prueba en la que se va a realizar una valoracion ")
	void testCreateValoracionOk() throws Exception {
		ModelMap model = new ModelMap();
		String view = valoracionController.createValoracion(TEST_VISITA_ID_Ok, model);
		assertEquals(view, "/visita/valoracion/createValoracionForm");
	}
	
	@WithMockUser(value = "bravo9", authorities = { "Cliente" })
	@Test
    @DisplayName("Prueba en la que se va a guardar una valoracion ")
	void testSaveValoracion() throws Exception {
		ModelMap model = new ModelMap();
		Valoracion newValoracion = new Valoracion();
		Optional<Visita> visita = visitaService.findById(TEST_VISITA_ID_Ok2);
		newValoracion.setComentario("Me parece muy buena");
		newValoracion.setPuntuacion(4);
		newValoracion.setVisita(visita.get());
		BindingResult bindingResult = new MapBindingResult(Collections.emptyMap(), "");
		String view = valoracionController.saveValoracion(newValoracion, model, bindingResult);
		assertEquals(view, "users/visitas");
		assertNotNull(model.get("success"));
	}
	
	@WithMockUser(value = "bravo9", authorities = { "Cliente" })
	@Test
    @DisplayName("Prueba en la que no se puede realizar una valoración ya que la visita ya tiene una ")
	void testCreateValoracionNotOk() throws Exception {
		ModelMap model = new ModelMap();
		String view = valoracionController.createValoracion(TEST_VISITA_ID_NotOk, model);
		assertEquals(view, "users/visitas");
	}

	@WithMockUser(value = "gilmar", authorities = { "propietario" })
	@Test
    @DisplayName("Prueba listar las valoraciones como propietario ")
	void testCreateValoracionLista() throws Exception {
		ModelMap model = new ModelMap();
		String view = valoracionController.getMisValoraciones(model);
		assertEquals(view, "valoracion/misValoraciones");
	}
	
}
