package org.springframework.inmocasa.web;

import static org.hamcrest.Matchers.hasProperty;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.inmocasa.InmocasaApplication;
import org.springframework.inmocasa.configuration.SecurityConfiguration;
import org.springframework.inmocasa.model.Propietario;
import org.springframework.inmocasa.model.Propietario;
import org.springframework.inmocasa.model.enums.Genero;
import org.springframework.inmocasa.service.PropietarioService;
import org.springframework.inmocasa.service.UsuarioService;
import org.springframework.inmocasa.service.PropietarioService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = PropietarioController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class), excludeAutoConfiguration = SecurityConfiguration.class)
@RunWith(SpringRunner.class)
public class PropietarioControllerTests {
	private static final int TEST_CLIENTE_ID = 1;

	@MockBean
	private PropietarioService propietarioService;
	
	@MockBean
	private UsuarioService usuarioService;

	@Autowired
	private MockMvc mockMvc;

	private Propietario propietario;
	

	@BeforeEach
	void setup() {

		propietario = new Propietario();
		propietario.setId(TEST_CLIENTE_ID);
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
	}

	@WithMockUser(username = "admin", password = "admin", authorities = "admin")
	@Test
	void testInitCreationFormSuccess() throws Exception {
		mockMvc.perform(get("/propietarios/new")).andExpect(status().isOk())
				.andExpect(model().attributeExists("propietario"))
				.andExpect(model().attribute("propietario", hasProperty("username")))
				.andExpect(view().name("propietarios/registroPropietarios"));
	}

	@WithMockUser(username = "gregorio23", password = "12345678b", authorities = "propietario")
	@Test
	void testProcessCreationFormSuccess() throws Exception {
		given(this.propietarioService.findByUsername(propietario.getUsername())).willReturn(propietario);
		mockMvc.perform(post("/propietarios/save").param("id", propietario.getId().toString()).with(csrf()))
				.andExpect(status().is2xxSuccessful());
	}

	@WithMockUser(username = "admin", password = "admin", authorities = "admin")
	@Test
	void testProcessCreationFormHasErrors() throws Exception {
		propietario.setId(20);
		propietario.setUsername("");
		this.propietarioService.savePropietario(propietario);

		Propietario c1 = this.propietarioService.findByUsername(propietario.getUsername());
		assertTrue(c1 == null);
	}

}
