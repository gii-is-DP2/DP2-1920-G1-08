package org.springframework.inmocasa.web.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.inmocasa.InmocasaApplication;
import org.springframework.inmocasa.model.Propietario;
import org.springframework.inmocasa.model.Propietario;
import org.springframework.inmocasa.model.Vivienda;
import org.springframework.inmocasa.model.enums.Genero;
import org.springframework.inmocasa.service.PropietarioService;
import org.springframework.inmocasa.web.PropietarioController;
import org.springframework.inmocasa.web.ViviendaController;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.MapBindingResult;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PropietarioControllerIntegrationTests {

	private static final int TEST_PROPIETARIO_ID = 1;

	@Autowired
	PropietarioController propietarioController;

	@Autowired
	PropietarioService propietarioService;

	@WithMockUser(value = "gilmar", authorities = { "propietario" })
	@Test
	void testInitUpdateForm() throws Exception {
		ModelMap model = new ModelMap();
		String view = propietarioController.editProfile(TEST_PROPIETARIO_ID, model);
		assertEquals(view, "propietarios/registroPropietarios");
		assertNotNull(model.get("propietario"));
	}

	@WithMockUser(value = "gilmar", authorities = { "propietario" })
	@Test
	void testProcessUpdateFormSuccess() throws Exception {
		ModelMap model = new ModelMap();
		Propietario propietario = new Propietario();
		propietario.setId(TEST_PROPIETARIO_ID);
		propietario.setNombre("Santiago");
		propietario.setApellidos("Martín");
		propietario.setDni("12345678D");
		propietario.setEmail("santimartinguay@gmail.com");
		propietario.setGenero(Genero.MASCULINO);
		propietario.setUsername("santiago");
		propietario.setPassword("santiago");
		propietario.setCif("12345678X");
		propietario.setEsInmobiliaria(true);
		propietario.setInmobiliaria("inmocasa");
		propietario.setFechaNacimiento(LocalDate.of(1998, 05, 31));

		BindingResult bindingResult = new MapBindingResult(Collections.emptyMap(), "");

		String view = propietarioController.processUpdateForm(propietario, bindingResult, model);

		assertEquals(view, "propietarios/profile");
	}

}
