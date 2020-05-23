package org.springframework.inmocasa.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import org.springframework.inmocasa.model.Usuario;
import org.springframework.inmocasa.service.AdministradorService;
import org.springframework.inmocasa.service.ClienteService;
import org.springframework.inmocasa.service.CompraService;
import org.springframework.inmocasa.service.PropietarioService;
import org.springframework.inmocasa.service.UsuarioService;
import org.springframework.inmocasa.service.VisitaService;
import org.springframework.inmocasa.service.ViviendaService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = ViviendaController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class), excludeAutoConfiguration = SecurityConfiguration.class)
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { InmocasaApplication.class })
public class UsuarioControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private VisitaService visitaService;

	@MockBean
	private ClienteService clienteService;

	@MockBean
	private PropietarioService propietarioService;

	@MockBean
	private UsuarioService usuarioService;
	
	@MockBean
	private ViviendaService viviendaService;
	
	@MockBean
	private CompraService compraService;
	
	@MockBean
	private AdministradorService adminstradorService;
	
	private Usuario usuario;
	
	@BeforeEach
	void setup() {
		usuario = new Usuario();
		usuario.setId(10);
		usuario.setUsername("alonso7");
		usuario.setPassword("alonso7");
		usuario.setNombre("Alonso");
		usuario.setDni("29504326H");
	}


	// HU-023: Borrar un usuario
	@WithMockUser(value = "alonso7", authorities = { "cliente" })
	@Test
	void testDeleteUsuarioOk() throws Exception {
		mockMvc.perform(get("/usuario/delete/{usuarioId}", 10)).andExpect(status().isOk());
	}

	// HU-023: Borrar un usuario (No se borra porque no hay nadie logueado)
	@Test
	void testDeleteUsuarioNotOk() throws Exception {
		mockMvc.perform(get("/usuario/delete/{usuarioId}", 1)).andExpect(status().is4xxClientError());
	}

}
