package org.springframework.test.inmocasa.web.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.hibernate.Hibernate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.inmocasa.InmocasaApplication;
import org.springframework.inmocasa.model.Propietario;
import org.springframework.inmocasa.model.Vivienda;
import org.springframework.inmocasa.service.PropietarioService;
import org.springframework.inmocasa.service.ViviendaService;
import org.springframework.inmocasa.web.ViviendaController;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.MapBindingResult;
import org.springframework.web.servlet.ModelAndView;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = { InmocasaApplication.class })
public class ViviendaControllerIntegrationTests {

	private static final int TEST_VIVIENDA_ID = 1;

	private static final int TEST_PROPIETARIO_ID = 1;

	@Autowired
	ViviendaController viviendaController;

	@Autowired
	ViviendaService viviendaService;

	@Autowired
	PropietarioService propietarioService;

	@Test
	void testInitUpdateForm() throws Exception {
		ModelMap model = new ModelMap();
		String view = viviendaController.editVivienda(TEST_VIVIENDA_ID, model);
		assertEquals(view, "viviendas/editVivienda");
		assertNotNull(model.get("vivienda"));
	}

//	@Test
//	void testProcessUpdateFormSuccess() throws Exception {
//		ModelMap model = new ModelMap();
//		Propietario propietario = propietarioService.findPropietarioById(TEST_PROPIETARIO_ID);
//		Vivienda newVivienda = new Vivienda();
//		newVivienda.setTitulo("Vivienda de prueba");
//		newVivienda.setFechaPublicacion(LocalDate.now());
//		newVivienda.setDireccion("C/mi casa");
//		newVivienda.setPrecio(1000);
//		newVivienda.setZona("Barrio");
//		newVivienda.setPropietario(propietario);
//		
//		BindingResult bindingResult = new MapBindingResult(Collections.emptyMap(), "");
//		
//		
//		String view = viviendaController.guardarPostActualizarVivienda(TEST_VIVIENDA_ID, newVivienda, bindingResult,
//				model);
//
//		assertEquals(view, "Viviendas/editVivienda");
//	}

	@Test
	void testShowVisitList() throws Exception {
		ModelAndView view = this.viviendaController.showVivienda(TEST_VIVIENDA_ID);

		Assertions.assertEquals(view, "viviendas/showViviendaDetails");
	}
}
