package org.springframework.inmocasa.web.integration;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.Collections;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.inmocasa.model.Propietario;
import org.springframework.inmocasa.model.Vivienda;
import org.springframework.inmocasa.model.form.FiltrosForm;
import org.springframework.inmocasa.service.PropietarioService;
import org.springframework.inmocasa.service.ViviendaService;
import org.springframework.inmocasa.web.ViviendaController;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.MapBindingResult;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ViviendaControllerIntegrationTests {

	private static final int TEST_VIVIENDA_ID = 1;

	private static final int TEST_VIVIENDA_ID_BORRAR = 2;

	private static final int TEST_PROPIETARIO_ID = 1;

	@Autowired
	ViviendaController viviendaController;

	@Autowired
	ViviendaService viviendaService;

	@Autowired
	PropietarioService propietarioService;

	@WithMockUser(value = "gilmar", authorities = { "propietario" })
	@Test
	void testInitUpdateForm() throws Exception {
		ModelMap model = new ModelMap();
		String view = viviendaController.editVivienda(TEST_VIVIENDA_ID, model);
		assertEquals(view, "viviendas/editVivienda");
		assertNotNull(model.get("vivienda"));
	}

	@WithMockUser(value = "gilmar", authorities = { "propietario" })
	@Test
	void testProcessUpdateFormSuccess() throws Exception {
		ModelMap model = new ModelMap();
		Propietario propietario = propietarioService.findPropietarioById(TEST_PROPIETARIO_ID);
		Vivienda newVivienda = new Vivienda();
		newVivienda.setId(TEST_VIVIENDA_ID);
		newVivienda.setTitulo("Vivienda de prueba");
		newVivienda.setFechaPublicacion(LocalDate.now());
		newVivienda.setDireccion("C/mi casa");
		newVivienda.setPrecio(1000);
		newVivienda.setZona("Barrio");

		BindingResult bindingResult = new MapBindingResult(Collections.emptyMap(), "");

		String view = viviendaController.guardarPostActualizarVivienda(TEST_VIVIENDA_ID, newVivienda, bindingResult,
				model);

		assertEquals(view, "viviendas/listNewViviendas");
	}
	
	@WithMockUser(value = "gilmar", authorities = { "propietario" })
	@Test
	@DisplayName("Guardar una vivienda nueva")
	void testProcessCreateViviendaSuccess() throws Exception {
		ModelMap model = new ModelMap();
		Propietario propietario = propietarioService.findByUsername("gilmar");
		Vivienda newVivienda = new Vivienda();
		newVivienda.setId(TEST_VIVIENDA_ID);
		newVivienda.setTitulo("Vivienda de prueba");
		newVivienda.setFechaPublicacion(LocalDate.now());
		newVivienda.setDireccion("C/mi casa");
		newVivienda.setPrecio(1000);
		newVivienda.setZona("Barrio");
		newVivienda.setFoto("");

		BindingResult bindingResult = new MapBindingResult(Collections.emptyMap(), "");

		String view = viviendaController.guardarVivienda(newVivienda, bindingResult, model);

		assertEquals(view, "viviendas/misViviendas");
	}

	@WithMockUser(value = "gilmar", authorities = { "propietario" })
	@Test
	void testListMisViviendas() throws Exception {
		ModelMap model = new ModelMap();
		String view = viviendaController.misViviendas(model);
		assertEquals(view, "viviendas/misViviendas");
	}

	@WithMockUser(value = "gilmar", authorities = { "propietario" })
	@Test
	void testShowViviendaDetails() throws Exception {
		ModelMap model = new ModelMap();
		String view = viviendaController.showVivienda(TEST_VIVIENDA_ID, model);
		assertEquals(view, "viviendas/showViviendaDetails");
	}

	@WithMockUser(value = "gilmar", authorities = { "propietario" })
	@Test
	@DisplayName("Prueba en la que se muestran todas las visitas")
	void testListViviendas() throws Exception {
		ModelMap model = new ModelMap();
		BindingResult bindingResult = new MapBindingResult(Collections.emptyMap(), "");
		String view = viviendaController.showListViviendas(model, null, bindingResult);
		assertEquals(view, "viviendas/listNewViviendas");
	}


	@DisplayName("Prueba borrar anuncio")
	@WithMockUser(value = "gilmar", authorities = { "propietario" })
	@Test
	void testDeleteViviendaOk() throws Exception {
		ModelMap model = new ModelMap();
		String view = viviendaController.borrarVivienda(model, TEST_VIVIENDA_ID_BORRAR);
		assertEquals(view, "viviendas/misViviendas");
	}
	
	@DisplayName("Prueba filtro")
	@WithMockUser(value = "housininmo", authorities = { "propietario" })
	@Test
	void testshowListViviendaFiltroOk() throws Exception {
		ModelMap model = new ModelMap();
		BindingResult bindingResult = new MapBindingResult(Collections.emptyMap(), "");
		FiltrosForm filtro = new FiltrosForm();
		filtro.setMax(600000);
		filtro.setMin(100);
		String view = viviendaController.showViviendasFiltros(model, filtro, bindingResult);
		assertEquals(view, "viviendas/listNewViviendas");
	}

}
