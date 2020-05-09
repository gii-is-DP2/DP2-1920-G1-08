package org.springframework.test.inmocasa.web;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.inmocasa.InmocasaApplication;
import org.springframework.inmocasa.configuration.SecurityConfiguration;
import org.springframework.inmocasa.model.Propietario;
import org.springframework.inmocasa.model.enums.Genero;
import org.springframework.inmocasa.service.ClienteService;
import org.springframework.inmocasa.service.PropietarioService;
import org.springframework.inmocasa.service.PropietarioService;
import org.springframework.inmocasa.web.PropietarioController;
import org.springframework.inmocasa.web.ViviendaController;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = PropietarioController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class), excludeAutoConfiguration = SecurityConfiguration.class)
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { InmocasaApplication.class })
public class PropietarioControllerTests {
	private static final int TEST_CLIENTE_ID = 1;

	@MockBean
	private ClienteService clienteService;
	
	@MockBean 
	private PropietarioService propietarioService;

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
		mockMvc.perform(get("/propietarios/new")).andExpect(status().isOk()).andExpect(model().attributeExists("propietario"))
				.andExpect(view().name("propietarios/registroPropietarios"));
	}
	

	@WithMockUser(username = "admin", password = "admin", authorities = "admin")
	@Test
	void testProcessCreationFormSuccess() throws Exception {
		mockMvc.perform(post("/propietarios/save")
				.param("nombre", "test1")
				.param("genero", "Genero.MASCULINO").with(csrf())
				.param("username", "gregorio")
				.param("password", "gregorio1")).andExpect(status().isOk())
				.andExpect(view().name("propietarios/registroPropietarios"));
	}
	
	
	@WithMockUser(username = "admin", password = "admin", authorities = "admin")
	@Test
	void testProcessCreationFormHasErrors() throws Exception {
		mockMvc.perform(post("/propietarios/save").with(csrf())
				.param("nombre", "189839")
				.param("genero", "MASCULINO").with(csrf())
				.param("username", "gregorio")
				.param("password", "gregorio1")).andExpect(status().isOk()).andExpect(model().attributeHasErrors("propietario"))
				.andExpect(view().name("propietarios/registroPropietarios"));
	}
	
	
	


}
