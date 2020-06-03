package org.springframework.inmocasa.web.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Collections;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.inmocasa.model.Denuncia;
import org.springframework.inmocasa.web.DenunciaController;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.MapBindingResult;
import org.springframework.validation.ObjectError;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DenunciaControllerIntegrationTests {

	private static final Integer TEST_ID_VIVIENDA = 1;
	private static final String VIEWS_DENUNCIA = "denuncias/nuevaDenuncia";
	private static final String VIEWS_DENUNCIA_REDIRECT = "redirect:/viviendas/allNew";
	
	@Autowired
	private DenunciaController denunciaController;
	
	@WithMockUser(value = "bravo9", authorities = { "Cliente" })
	@Test
    @DisplayName("Prueba en la que se va a crear el formulario de denuncia ")
	void testCreateDenunciaForm() throws Exception {
		ModelMap model = new ModelMap();
		
		String view = denunciaController.create(TEST_ID_VIVIENDA, model);
		assertEquals(view,VIEWS_DENUNCIA);
	}
	
	@WithMockUser(value = "bravo9", authorities = { "Cliente" })
	@Test
    @DisplayName("Prueba en la que se va a realizar una denuncia ")
	void testCreateDenunciaOk() throws Exception {
		ModelMap model = new ModelMap();
		Denuncia den = new Denuncia();
		den.setJustificacion("lo que sea");
		BindingResult bindingResult = new MapBindingResult(Collections.emptyMap(), "");
		String view = denunciaController.save(TEST_ID_VIVIENDA,den, bindingResult, model);
		assertEquals(view,VIEWS_DENUNCIA_REDIRECT);
	}
	
	@WithMockUser(value = "bravo9", authorities = { "Cliente" })
	@Test
    @DisplayName("Prueba en la que se va a realizar una denuncia sin justificacion ")
	void testCreateDenunciaNotOk() throws Exception {
		ModelMap model = new ModelMap();
		Denuncia den = new Denuncia();
		BindingResult bindingResult = new MapBindingResult(Collections.emptyMap(), "");
		bindingResult.addError(new ObjectError("Justificacion", "La justificación es obligatoria"));
		String view = denunciaController.save(TEST_ID_VIVIENDA,den, bindingResult, model);
		assertEquals(view,VIEWS_DENUNCIA);
	}
}
